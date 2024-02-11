package com.vedruna.twitterapi2ev.persistence.services;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServiceI {

    //Editar descripción (privado)
    //Buscar usuario por username (público)
    //Obtener todos los usuarios que sigue un usuario (privado)
    //Obtener todos los usuarios que siguen a un usuario (privado)

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    Optional<UserDTO> getUserByUsername(String username);

    void register(String username, String password, String email, String description);

    void login(String username, String password);

    UserDTO editDescription(Long id, String description);

    void follow(Long follower_id, Long toFollow_id);

    List<UserDTO> getFollowers(Long id);

    List<UserDTO> getFollowed(Long id);
}
