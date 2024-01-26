package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService implements UserServiceI {
    @Autowired
    private UserRepositoryI userRepo;
    User user;

    @Transactional
    @Override
    public UserDTO editDescription(Long userId, String description) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setDescription(description);
            userRepo.save(user);
        }
        return new UserDTO(user.getUserId(), user.getUserName(), user.getDescription());
    }

    @Override
    public UserDTO searchUserByUsername(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return new UserDTO(user.getUserId(), user.getUserName(), user.getDescription());
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
                    .map(u -> new UserDTO(u.getUserId(), u.getUserName(), u.getDescription()))
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
                    .map(u -> new UserDTO(u.getUserId(), u.getUserName(), u.getDescription()))
                    .collect(Collectors.toList());

            return followedDTO;
        }
        return null;
    }
}
