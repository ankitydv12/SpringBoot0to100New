package com.ankit.module4.services;

import com.ankit.module4.dto.PostDTO;

import java.util.List;


public interface PostService {
    List<PostDTO> getAllPost();

    PostDTO createNewPost(PostDTO input);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(Long postId, PostDTO request);
}
