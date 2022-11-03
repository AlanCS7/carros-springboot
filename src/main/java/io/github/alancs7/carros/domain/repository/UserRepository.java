package io.github.alancs7.carros.domain.repository;

import io.github.alancs7.carros.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
