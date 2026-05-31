package com.ankit.module5springsecurity.services.Impl;

import com.ankit.module5springsecurity.dto.PostDTO;
import com.ankit.module5springsecurity.entities.PostEntity;
import com.ankit.module5springsecurity.exception.ResourceNotFoundException;
import com.ankit.module5springsecurity.repository.PostRepository;
import com.ankit.module5springsecurity.services.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO input) {
        PostEntity postEntity = toEntity(input);
        postEntity = postRepository.save(postEntity);
        return toDto(postEntity);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Resource is not found"));
        return toDto(postEntity);
    }

    @Transactional
    @Override
    public PostDTO updatePost(Long postId, PostDTO request) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Resource Not found"));
        postEntity.setDescription(request.getDescription());
        postEntity.setTitle(request.getTitle());
        return toDto(postEntity);
    }

    private PostDTO toDto(PostEntity postEntity) {
        return new PostDTO(postEntity.getId(), postEntity.getTitle(), postEntity.getDescription());
    }

    private PostEntity toEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(postDTO.getId());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setDescription(postDTO.getDescription());
        return postEntity;
    }
}
