package io.github.alancs7.carros;

import io.github.alancs7.carros.domain.dto.CarroDTO;
import io.github.alancs7.carros.domain.entity.Carro;
import io.github.alancs7.carros.service.CarroService;
import io.github.alancs7.carros.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarrosServiceTest {

    @Autowired
    private CarroService service;

    @Test
    void deveriaInserirOCarroEDeletar() {


        Carro carro = new Carro();
        carro.setNome("Golf");
        carro.setTipo("esportivos");

        // Inserindo o carro
        CarroDTO c = service.insert(carro);

        // Garantindo que ele não está nulo e foi inserido
        assertNotNull(c);
        assertNotNull(c.getId());

        //Buscar o objeto
        c = service.getCarroById(c.getId());
        assertNotNull(c);

        // Garantindo que foi criado com as informações corretas
        assertEquals("Golf", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // deletar o objeto
        service.delete(c.getId());

        // verificar se deletou
        CarroDTO finalC = c;
        assertThrows(ObjectNotFoundException.class, () -> service.getCarroById(finalC.getId()));

//        try {
//            assertNull(service.getCarroById(c.getId()));
//            fail("Carro não excluido");
//        } catch (ObjectNotFoundException e) {
//        }
    }

    @Test
    public void deveriaRetornarUmaListaDeCarro() {
        List<CarroDTO> carros = service.getCarros();

        assertEquals(30, carros.size());
    }

    @Test
    public void deveriaRetornarUmaFerrari() {
        CarroDTO op = service.getCarroById(11L);

        assertNotNull(op);

        assertEquals("Ferrari FF", op.getNome());
    }

    @Test
    public void deveriaRetornarUmaListaDeCarrosPorTipo() {

        assertEquals(10, service.getAllByTipo("classicos").size());
        assertEquals(10, service.getAllByTipo("esportivos").size());
        assertEquals(10, service.getAllByTipo("luxo").size());
        assertEquals(0, service.getAllByTipo("x").size());
    }
}
