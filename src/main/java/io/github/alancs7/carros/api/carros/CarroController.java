package io.github.alancs7.carros.api.carros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity getCarros() {
        return ResponseEntity.ok(service.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarroById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCarroById(id));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity insert(@RequestBody Carro carro) {
        CarroDTO c = service.insert(carro);

        URI location = getUri(carro.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getAllByTipo(@PathVariable String tipo) {
        List<CarroDTO> carros = service.getAllByTipo(tipo);

        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Carro carro) {
        CarroDTO c = service.update(carro, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.ok().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
