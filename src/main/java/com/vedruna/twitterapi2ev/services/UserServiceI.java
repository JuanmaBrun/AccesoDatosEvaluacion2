package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserServiceI {

    UserDTO editDescription(Long userId, String description);

    UserDTO searchUserByUsername(String username);

    List<UserDTO> getFollowers(Long userId);

    List<UserDTO> getFollowed(Long userId);
}
