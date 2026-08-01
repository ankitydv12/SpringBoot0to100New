package com.ankit.module5springsecurity.controllers;

import com.ankit.module5springsecurity.advice.ApiResponse;
import com.ankit.module5springsecurity.dto.PostDTO;
import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ApiResponse<PostDTO> createPost(@RequestBody PostDTO input)
    {
        System.out.println("POST API HIT");
        System.out.println(input);

        PostDTO op = postService.createNewPost(input);

        System.out.println("Saved = " + op);

        return new ApiResponse<>(op);
    }

    @GetMapping("/{postId}")
    PostDTO getPostById(@PathVariable Long postId)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}",user);
        return postService.getPostById(postId);
    }

    @PatchMapping("/{postId}")
    PostDTO updatePost(@PathVariable Long postId , @RequestBody PostDTO request)
    {
        return postService.updatePost(postId,request);
    }
}
