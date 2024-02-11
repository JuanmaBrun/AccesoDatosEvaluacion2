package com.vedruna.twitterapi2ev.persistence.repositories;

import com.vedruna.twitterapi2ev.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryI extends JpaRepository<User, Integer> {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
