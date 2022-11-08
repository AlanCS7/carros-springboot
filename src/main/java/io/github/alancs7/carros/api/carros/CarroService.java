package io.github.alancs7.carros.api.carros;

import io.github.alancs7.carros.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> getCarros() {
        return repository.findAll().stream().map(CarroDTO::create).toList();
    }

    public CarroDTO getCarroById(Long id) {
        return repository.findById(id).map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Car not found"));
    }

    public List<CarroDTO> getAllByTipo(String tipo) {
        return repository.findByTipo(tipo).stream().map(CarroDTO::create).toList();
    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
        return CarroDTO.create(repository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possivel atualizar o registro");
        return repository.findById(id).map(c -> {
                    c.setNome(carro.getNome());
                    c.setTipo(carro.getTipo());
                    repository.save(c);
                    return CarroDTO.create(c);
                }
        ).orElse(null);

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
