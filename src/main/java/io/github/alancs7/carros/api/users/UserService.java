package io.github.alancs7.carros.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> getUsers() {
        return repository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }
}
