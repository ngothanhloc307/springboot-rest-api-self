package com.demo.restapi.service;

import com.demo.restapi.entity.Post;
import com.demo.restapi.errors.ResourceNotFoundException;
import com.demo.restapi.repository.PostRepository;
import com.demo.restapi.utils.request.PostDTO;
import com.demo.restapi.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.restapi.service.CommentServiceImpl.mapperToCommentDTO;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(p -> mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        post.setMaximumOfComments(dto.getMaximumOfComments());

        Post savePost = postRepository.save(post);
        return mapperToPostDTO(savePost);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        post.setMaximumOfComments(dto.getMaximumOfComments());

        return mapperToPostDTO(postRepository.save(post));
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        postRepository.deleteById(id);
        return "Delete successfully";
    }

    private PostResponseDTO mapperToPostDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setMaximumOfComments(post.getMaximumOfComments());
        if (post.getComments() != null && post.getComments().size() > 0) {
            dto.setComments(post.getComments().stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toSet()));
        }

        return dto;
    }
}
