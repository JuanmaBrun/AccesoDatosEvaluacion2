package com.vedruna.twitterapi2ev.persistence.repositories;

import com.vedruna.twitterapi2ev.persistence.model.Post;
import com.vedruna.twitterapi2ev.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepositoryI extends JpaRepository<Post, Long> {

    List<Post> findPostByAuthor(User author);

    List<Post> findByAuthorIn(List<User> followedUsers);
}
