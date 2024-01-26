package com.vedruna.twitterapi2ev.services;

import com.vedruna.twitterapi2ev.dto.PostDTO;

import java.util.List;

public interface PostServiceI {

    //Obtener todas las publicaciones (privado)
    //Obtener todas las publicaciones de un usuario (público)
    //Obtener todas las publicaciones de los usuarios que sigues
    //Insertar publicación (privado)
    //Editar publicación (privado)
    //Borrar publicación (privado)

    List<PostDTO> searchAllPost();

    List<PostDTO> searchPostByUserId(Long id, Long userrId);

    List<PostDTO> getPostsByFollowedUsers(Long userId);

    void createPost(Long userId, PostDTO postDTO);

    void editPost(Long postId, PostDTO postDTO);

    void deletePost(Long postId);

}
