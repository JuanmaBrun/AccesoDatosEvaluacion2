package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServiceI {

    void registro(String username, String password, String email);

    UserDTO login(String username, String password);

    UserDTO editDescription(Long userId, String description);

    UserDTO searchUserByUsername(String username);

    List<UserDTO> getFollowers(Long userId);

    List<UserDTO> getFollowed(Long userId);
}
