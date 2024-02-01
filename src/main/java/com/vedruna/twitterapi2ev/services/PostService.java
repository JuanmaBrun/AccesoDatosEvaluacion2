package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.PostDTO;
import com.vedruna.twitterapi2ev.dto.UserDTO;
import com.vedruna.twitterapi2ev.persistence.model.Post;
import com.vedruna.twitterapi2ev.persistence.model.User;
import com.vedruna.twitterapi2ev.persistence.repository.PostRepositoryI;
import com.vedruna.twitterapi2ev.persistence.repository.UserRepositoryI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements PostServiceI {

    private PostRepositoryI postRepo;

    private UserRepositoryI userRepo;

    Post post;


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
    public void createPost(Long userId, String text, PostDTO postDTO) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            Post newPost = new Post();
            newPost.setText(text);
            newPost.setAuthor(user);
            newPost.setCreateDate(LocalDate.now());
            postRepo.save(newPost);
            System.out.println("Publicacion creada con exito");
        }
    }

    @Override
    public PostDTO editPost(Long postId, PostDTO postDTO, String text) {
        Optional<Post> optionalPost = postRepo.findById(postId);
        post = optionalPost.get();
        post.setText(text);
        post.setEditDate(LocalDate.now());
        postRepo.save(post);
        return new PostDTO(post.getPublicationId(), post.getAuthor(), post.getText());
    }

    @Override
    public void deletePost(Long postId) {
        Optional<Post> optionalPost = postRepo.findById(postId);
        Post postToDelete = optionalPost.get();
        postRepo.delete(postToDelete);
    }

    private List<PostDTO> mapPostsToDTOs(List<Post> posts) {
        return posts.stream()
                .map(post -> new PostDTO(post.getPublicationId(), post.getAuthor(), post.getText()))
                .collect(Collectors.toList());
    }
}
