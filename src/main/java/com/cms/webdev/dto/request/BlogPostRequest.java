package com.cms.webdev.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@ToString
public class BlogPostRequest {
    @NotBlank(message = "blogpost.slug.empty")
    private String slug;
    @NotBlank(message = "blogpost.title.empty")
    private String title;
    @NotBlank(message = "blogpost.content.empty")
    private String content;
    @NotNull(message = "userId.empty.message")
    private Integer userId;

    private Long parentId;
}
