package com.vedruna.twitterapi2ev.controllers;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.persistence.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserServiceI userServiceI;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userServiceI.getAllUsers();

        if (!allUsers.isEmpty()) {
            return ResponseEntity.ok(allUsers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("getById/{id}")
    public Optional<UserDTO> getById(@PathVariable Long id) {
        return userServiceI.getUserById(id);
    }

    @GetMapping("getByUsername/{username}")
    public Optional<UserDTO> getByUsername(@PathVariable String username) {
        return userServiceI.getUserByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userServiceI.register(user.getUsername(), user.getPassword(), user.getEmail(), user.getDescription());
        return ResponseEntity.ok("Edit exitoso");
    }


}
