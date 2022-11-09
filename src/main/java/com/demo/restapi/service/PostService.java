package com.demo.restapi.service;

import com.demo.restapi.utils.request.PostDTO;
import com.demo.restapi.utils.response.PostResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostResponseDTO> getAll();

    Optional<PostResponseDTO> findById(Long id);

    PostResponseDTO save(PostDTO dto);

    PostResponseDTO update(PostDTO dto, Long id);

    String delete(Long id);
}
