package io.github.alancs7.carros.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity get() {
        List<UserDTO> list = service.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/userInfo")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {
        return UserDTO.create(user);
    }
}
