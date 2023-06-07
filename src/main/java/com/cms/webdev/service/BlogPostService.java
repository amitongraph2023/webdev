package com.cms.webdev.service;

import com.cms.webdev.dto.request.BlogPostRequest;
import com.cms.webdev.dto.response.BlogPostDto;

import java.util.List;

public interface BlogPostService {
    BlogPostDto createPost(BlogPostRequest blogPostRequest);

    List<BlogPostDto> getAllBlogsByUserId(Integer userId);

    BlogPostDto getBlogPostById(Long blogPostId);

    List<BlogPostDto> getAllChildBlogsByParentId(Long parentId);

    List<BlogPostDto> getAllParentBlogsByChildId(Long childId);

    BlogPostDto updateBlogPost(BlogPostRequest blogPostRequest,long blogId);

    boolean deleteBlogPost(long blogId);
}
