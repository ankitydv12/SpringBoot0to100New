package com.ankit.module5springsecurity.controllers;

import com.ankit.module5springsecurity.dto.PostDTO;
import com.ankit.module5springsecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping
    public List<PostDTO> getAllPost()
    {
        return postService.getAllPost();
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO input)
    {
        return postService.createNewPost(input);
    }

    @GetMapping("/{postId}")
    PostDTO getPostById(@PathVariable Long postId)
    {
        return postService.getPostById(postId);
    }

    @PatchMapping("/{postId}")
    PostDTO updatePost(@PathVariable Long postId , @RequestBody PostDTO request)
    {
        return postService.updatePost(postId,request);
    }
}
