package com.vedruna.twitterapi2ev.persistence.repository;

import com.vedruna.twitterapi2ev.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryI extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
