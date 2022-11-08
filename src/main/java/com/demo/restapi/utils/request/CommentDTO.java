package com.demo.restapi.utils.request;

import lombok.Data;

@Data
public class CommentDTO {
    private String name;

    private String email;

    private String body;

    private Long postId;
}
