package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.persistence.repository.UserRepositoryI;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserRepositoryI userRepo;
    User user;

    @Override
    public void registro(String username, String password, String email) {
        if (userRepo.existsByUsername(username)) {
            System.out.println("Ya existe");
        } else if (userRepo.existsByEmail(email)) {
            System.out.println("Ya existe");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashPassword(password));
        newUser.setEmail(email);
        newUser.setCreateDate(LocalDate.now());
        userRepo.save(newUser);

    }

    @Override
    public UserDTO login(String username, String password) {
        return null;
    }

    @Transactional
    @Override
    public UserDTO editDescription(Long userId, String description) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setDescription(description);
            userRepo.save(user);
        }
        return new UserDTO(user.getUsername(), user.getDescription());
    }

    @Override
    public UserDTO searchUserByUsername(String username) {
        User optionalUser = userRepo.findByUsername(username);
        if (optionalUser != null) {
            return new UserDTO(user.getUsername(), user.getDescription());
        }
        return null;
    }

    @Override
    public List<UserDTO> getFollowers(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Obtener la lista de seguidores del usuario
            List<User> followers = user.getFollowers();

            // Convertir la lista de usuarios a una lista de DTOs
            List<UserDTO> followersDTO = followers.stream()
                    .map(u -> new UserDTO(u.getUsername(), u.getDescription()))
                    .collect(Collectors.toList());

            return followersDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> getFollowed(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();

            // Obtener la lista de usuarios seguidos por el usuario
            List<User> followed = user.getFollowed();

            // Convertir la lista de usuarios a una lista de DTOs
            List<UserDTO> followedDTO = followed.stream()
                    .map(u -> new UserDTO(u.getUsername(), u.getDescription()))
                    .collect(Collectors.toList());

            return followedDTO;
        }
        return null;
    }


    private static String hashPassword(String password) {
        // El método hashpw de BCrypt toma la contraseña y la "saltea" antes de encriptarla
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}

