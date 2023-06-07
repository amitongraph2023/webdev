package com.cms.webdev.dto.response;

import com.cms.webdev.dto.BaseDto;
import com.cms.webdev.dto.request.BlogPostRequest;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class BlogPostDto extends BaseDto<Long> {
    private String slug;
    private String title;
    private String content;
    private List<BlogPostDto> subBlogs;
    //private List<Long> parentBlogs;
}
