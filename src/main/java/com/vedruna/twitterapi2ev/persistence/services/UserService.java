package com.vedruna.twitterapi2ev.persistence.services;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.Role;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.persistence.repositories.UserRepositoryI;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceI {

    @Autowired
    private UserRepositoryI userRepositoryI;
    Date currentDate = new Date();
    User user;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepositoryI.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User u : allUsers) {
            usersDTO.add(new UserDTO(u.getId(), u.getUsername(), u.getDescription()));
        }

        return usersDTO;
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> userOptional = userRepositoryI.findById(id);

        return userOptional.map(user -> new UserDTO(user.getId(), user.getUsername(), user.getDescription()));
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        Optional<User> userOptional = userRepositoryI.findByUsername(username);

        return userOptional.map(user -> new UserDTO(user.getId(), user.getUsername(), user.getDescription()));
    }

    @Override
    public void register(String username, String password, String email, String description) {
        if (userRepositoryI.existsByUsername(username)) {
            System.out.println("Ya existe un usario con este nombre");
        } else if (userRepositoryI.existsByEmail(email)) {
            System.out.println("Ya existe un usuario con este email");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashPassword(password));
        newUser.setEmail(email);
        newUser.setCreateDate(currentDate);
        newUser.setDescription(description);
        user.setRole(Role.USER);
        userRepositoryI.save(newUser);
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public UserDTO editDescription(Long id, String description) {
        Optional<User> optionalUser = userRepositoryI.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setDescription(description);
            userRepositoryI.save(user);
        }

        return new UserDTO(user.getId(), user.getUsername(), user.getDescription());
    }

    @Override
    public void follow(Long follower_id, Long toFollow_id) {
        User follower = userRepositoryI.findById(follower_id).orElseThrow(() -> new RuntimeException("Follower not found"));
        User toFollow = userRepositoryI.findById(toFollow_id).orElseThrow(() -> new RuntimeException("User to follow not found"));

        // Agregar el seguidor a la lista de seguidores del usuario a seguir
        toFollow.getFollowers().add(follower);

        // Agregar el usuario a seguir a la lista de seguidos del seguidor
        follower.getFollowing().add(toFollow);

        // Guardar los cambios en la base de datos
        userRepositoryI.save(follower);
        userRepositoryI.save(toFollow);
    }

    @Override
    public List<UserDTO> getFollowers(Long id) {
        Optional<User> optionalUser = userRepositoryI.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<User> followers = user.getFollowers();

            if (followers != null && !followers.isEmpty()) {
                // Convertir la lista de usuarios a una lista de DTOs
                return followers.stream()
                        .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getDescription()))
                        .collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public List<UserDTO> getFollowed(Long id) {
        Optional<User> optionalUser = userRepositoryI.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<User> following = user.getFollowing();

            if (following != null && !following.isEmpty()) {
                // Convertir la lista de usuarios a una lista de DTOs
                return following.stream()
                        .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getDescription()))
                        .collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
