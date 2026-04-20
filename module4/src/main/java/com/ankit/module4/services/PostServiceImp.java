package com.ankit.module4.services;

import com.ankit.module4.Exception.ResourceNotFoundException;
import com.ankit.module4.dto.PostDTO;
import com.ankit.module4.entities.PostEntity;
import com.ankit.module4.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity, PostDTO.class)).toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO input) {
        PostEntity postEntity = modelMapper.map(input, PostEntity.class);
        postEntity = postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDTO.class);
    }

    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Resource is not found"));
        return modelMapper.map(postEntity, PostDTO.class);

    }

    @Transactional
    @Override
    public PostDTO updatePost(Long postId, PostDTO request) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Resource Not found"));
        postEntity.setDescription(request.getDescription());
        postEntity.setTitle(request.getTitle());
        return modelMapper.map(postEntity,PostDTO.class);
    }
}
