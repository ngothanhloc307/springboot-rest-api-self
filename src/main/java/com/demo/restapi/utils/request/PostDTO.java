package com.demo.restapi.utils.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import javax.validation.constraints.NotNull;

@Data
public class PostDTO {

    private Long id;

    @NotNull(message = "Title is require")
    @NotEmpty(message = "Field Title is not null")
    @Length(min = 1, max = 30, message = "Invalid length field title")
    private String title;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is not empty")
    @Length(min = 1, max = 30, message = "Invalid length field Description")
    private String description;

    @Min(1)
    @Max(6)
    @NotNull(message = "Maximum of comments is required")
    private Integer maximumOfComments;

    @NotNull(message = "Field content is require")
    @NotEmpty(message = "Field content is not empty")
    @Length(min = 1, max = 30, message = "Invalid length field content")
    private String content;
}
