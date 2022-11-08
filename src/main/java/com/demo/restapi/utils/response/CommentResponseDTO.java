package com.demo.restapi.utils.response;

import com.demo.restapi.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CommentResponseDTO {
    private Long id;

    private String name;

    private String email;

    private String body;

    @JsonIgnore
    private Post post;
}
