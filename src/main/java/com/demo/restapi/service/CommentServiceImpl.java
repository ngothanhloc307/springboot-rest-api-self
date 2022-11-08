package com.demo.restapi.service;

import com.demo.restapi.entity.Comment;
import com.demo.restapi.repository.CommentRepository;
import com.demo.restapi.utils.request.CommentDTO;
import com.demo.restapi.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments =commentRepository.findAll();
        return comments.stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        Comment comment = commentRepository.findById(id).get();
        return Optional.of(mapperToCommentDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Comment cmt = new Comment();
        cmt.setName(dto.getName());
        cmt.setEmail(dto.getEmail());
        cmt.setBody(dto.getBody());
        //cmt.setPost(p);
        Comment savedComment = commentRepository.save(cmt);
        return mapperToCommentDTO(savedComment);
    }


    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id).get();

        comment.setName(dto.getName());
        comment.setBody(dto.getBody());
        comment.setEmail(dto.getEmail());
        //comment.setPost(p);
        return mapperToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Success";
    }

    public static CommentResponseDTO mapperToCommentDTO(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setPost(comment.getPost());
        return dto;
    }
}
