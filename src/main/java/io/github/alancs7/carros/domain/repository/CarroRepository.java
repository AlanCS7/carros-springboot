package io.github.alancs7.carros.domain.repository;

import io.github.alancs7.carros.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);

}
