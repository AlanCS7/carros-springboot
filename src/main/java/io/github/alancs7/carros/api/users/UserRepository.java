package io.github.alancs7.carros.api.users;

import io.github.alancs7.carros.api.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
