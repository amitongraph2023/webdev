package com.cms.webdev.controller;

import com.cms.webdev.common.annotations.BaseURL;
import com.cms.webdev.common.handler.response.ResponseDTO;
import com.cms.webdev.common.handler.response.ResponseUtil;
import com.cms.webdev.dto.request.BlogPostRequest;
import com.cms.webdev.dto.response.BlogPostDto;
import com.cms.webdev.service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseURL
@RestController
public class BlogPostController {
    private final ResponseUtil responseUtil;
    private final BlogPostService blogPostService;
    BlogPostController(ResponseUtil responseUtil, BlogPostService blogPostService) {
        this.responseUtil = responseUtil;
        this.blogPostService = blogPostService;
    }

    @PostMapping("blog-post/create")
    public ResponseDTO<BlogPostDto> createBlog(@Valid @RequestBody BlogPostRequest blogPostRequest) {
        return responseUtil.ok(blogPostService.createPost(blogPostRequest));
    }

    @GetMapping("blog-post/getAllBlogsByUserId")
    public ResponseDTO<List<BlogPostDto>> getBlogList(@RequestParam int userId) {
        return responseUtil.ok(blogPostService.getAllBlogsByUserId(userId));
    }
    @GetMapping("blog-post/{id}")
    public ResponseDTO<List<BlogPostDto>> getByBlogId(@PathVariable long id) {
        return responseUtil.ok(blogPostService.getBlogPostById(id));
    }
    @GetMapping("blog-post/getSubBlogsByParentBlogId")
    public ResponseDTO<List<BlogPostDto>> getSubBlogsByParentBlogId(@RequestParam long parentBlogId) {
        return responseUtil.ok(blogPostService.getAllChildBlogsByParentId(parentBlogId));
    }

    @PutMapping("blog-post/{id}/update")
    public ResponseDTO<List<BlogPostDto>> update(@RequestBody BlogPostRequest blogPostRequest,@PathVariable long id) {
        return responseUtil.ok(blogPostService.updateBlogPost(blogPostRequest,id));
    }

    @PutMapping("blog-post/{id}/delete")
    public ResponseDTO<Boolean> delete(@PathVariable long id) {
        return responseUtil.ok(blogPostService.deleteBlogPost(id));
    }

}
