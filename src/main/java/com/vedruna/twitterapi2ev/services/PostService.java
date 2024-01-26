package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.PostDTO;
import com.vedruna.twitterapi2ev.persistence.model.Post;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.persistence.repository.PostRepositoryI;
import com.vedruna.twitterapi2ev.persistence.repository.UserRepositoryI;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PostService implements PostServiceI {

    private PostRepositoryI postRepo;

    private UserRepositoryI userRepo;

    @Override
    public List<PostDTO> searchAllPost() {
        List<Post> allPosts = postRepo.findAll();
        return mapPostsToDTOs(allPosts);

    }

    @Override
    public List<PostDTO> searchPostByUserId(Long id, Long userId) {
        List<Post> userPosts = postRepo.findPostByAuthor(userRepo.findById(userId).orElse(null));
        return mapPostsToDTOs(userPosts);
    }

    @Override
    public List<PostDTO> getPostsByFollowedUsers(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            List<User> followedUsers = user.getFollowed();
            List<Post> postsByFollowedUsers = postRepo.findByAuthorIn(followedUsers);
            return mapPostsToDTOs(postsByFollowedUsers);
        }
        return null;
    }

    @Override
    public void createPost(Long userId, PostDTO postDTO) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            Post newPost = new Post(user, postDTO.getText(), LocalDate.now(), null, user);
            postRepo.save(newPost);
        }
    }

    @Override
    public void editPost(Long postId, PostDTO postDTO) {

    }

    @Override
    public void deletePost(Long postId) {

    }

    private List<PostDTO> mapPostsToDTOs(List<Post> posts) {
        return posts.stream()
                .map(post -> new PostDTO(post.getPublicationId(), post.getAuthor(), post.getText()))
                .collect(Collectors.toList());
    }
}
