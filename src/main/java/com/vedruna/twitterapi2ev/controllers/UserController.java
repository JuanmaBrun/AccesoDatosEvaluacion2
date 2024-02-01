package com.vedruna.twitterapi2ev.controllers;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.registro(user.getUsername(),user.getPassword(), user.getEmail());
        return ResponseEntity.ok("Registro exitoso");
    }
}

