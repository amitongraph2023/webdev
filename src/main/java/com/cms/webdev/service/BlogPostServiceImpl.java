package com.cms.webdev.service;

import com.cms.webdev.common.handler.exceptions.AppException;
import com.cms.webdev.common.utils.ErrorMessage;
import com.cms.webdev.dto.request.BlogPostRequest;
import com.cms.webdev.dto.response.BlogPostDto;
import com.cms.webdev.entity.BlogPost;
import com.cms.webdev.entity.User;
import com.cms.webdev.repository.BlogPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService{
    private final BlogPostRepository blogPostRepository;
    private final UserService userService;

    BlogPostServiceImpl(BlogPostRepository blogPostRepository, UserService userService) {
        this.blogPostRepository = blogPostRepository;
        this.userService = userService;
    }
    @Override
    @Transactional
    public BlogPostDto createPost(BlogPostRequest blogPostRequest) {
        BlogPost blogPost = new BlogPost();
        BlogPost parentBlog = null;

        User user = userService.findByUserId(blogPostRequest.getUserId());

        if(Objects.isNull(user)) {
            throw new AppException(ErrorMessage.USER_NOT_EXIST, HttpStatus.FORBIDDEN);
        }

        if(blogPostRequest.getParentId() != null) {
            parentBlog = blogPostRepository.findById(blogPostRequest.getParentId()).orElseThrow(
                    () -> new AppException(ErrorMessage.PARENT_BLOG_POST_NOT_EXISTS, HttpStatus.NOT_FOUND)
            );
        }

        if(isSlugTaken(blogPostRequest.getSlug())) {
            throw new AppException(ErrorMessage.SLUG_NOT_AVAILABLE, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        logger.info("creating new blog for user with id {} and slug {}", blogPostRequest.getUserId(), blogPostRequest.getSlug());
        long rightBoundary = parentBlog != null ? parentBlog.getRgt() : 0;
        // Update the left and right boundaries of existing blogs
        blogPostRepository.updateLeftAndRightBoundaries(rightBoundary);
        blogPost.setContent(blogPostRequest.getContent())
                .setTitle(blogPostRequest.getTitle())
                .setSlug(blogPostRequest.getSlug())
                .setParent(parentBlog)
                .setUser(user)
                .setLft(rightBoundary+1)
                .setRgt(rightBoundary+2)
                .setIsDeleted(false);
                


        blogPost=blogPostRepository.save(blogPost);
        return convertToDto(blogPost);
    }

    @Override
    public List<BlogPostDto> getAllBlogsByUserId(Integer userId) {
        logger.info("Fetching blogs by userId:{}",userId);
        List<BlogPost> blogPostList=blogPostRepository.findAllOrderedByLeftBoundary(userId);
        return convertToDto(blogPostList);
    }

    @Override
    public BlogPostDto getBlogPostById(Long blogPostId) {
        logger.info("Fetching blog details by blog ID:{}",blogPostId);
        return convertToDto(blogPostRepository.findById(blogPostId).orElseThrow(
                () -> new AppException(ErrorMessage.BLOG_POST_NOT_EXISTS, HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<BlogPostDto> getAllChildBlogsByParentId(Long parentId) {
        logger.info("Fetching sub blogs by parent blog ID:{}",parentId);
        return convertToDto(blogPostRepository.findAllSubBlogsByParentBlogId(parentId));
    }

    @Override
    public List<BlogPostDto> getAllParentBlogsByChildId(Long childId) {
        return null;
    }

    private Boolean isSlugTaken(String slug) {
        return blogPostRepository.existsBySlug(slug);
    }

    @Override
    public BlogPostDto updateBlogPost(BlogPostRequest blogPostRequest, long blogId){

        BlogPost blogPost=blogPostRepository.findById(blogId).orElseThrow(
                () -> new AppException(ErrorMessage.BLOG_POST_NOT_EXISTS, HttpStatus.NOT_FOUND));
        logger.info("updating the blog with id:{}",blogId);
        blogPost.setContent(blogPostRequest.getContent());
        blogPost.setTitle(blogPostRequest.getTitle());
        blogPost.setSlug(blogPostRequest.getSlug());
        return convertToDto(blogPostRepository.save(blogPost));
    }
    @Override
    @Transactional
    public boolean deleteBlogPost(long blogId){
        blogPostRepository.findById(blogId).orElseThrow(
                () -> new AppException(ErrorMessage.BLOG_POST_NOT_EXISTS, HttpStatus.NOT_FOUND));
        logger.info("deleting the blog & sub blogs with parent blog id:{}",blogId);
        blogPostRepository.deleteBlogPostAndSubBlogs(blogId);
        return true;
    }
    private List<BlogPostDto> convertToDto(List<BlogPost> blogPosts) {
        List<BlogPostDto> dtos = new ArrayList<>();
        for (BlogPost blogPost : blogPosts) {
            dtos.add(convertToDto(blogPost));
        }
        return dtos;
    }

    private BlogPostDto convertToDto(BlogPost blogPost) {
        BlogPostDto dto = new BlogPostDto();
        dto.setTitle(blogPost.getTitle())
                .setSlug(blogPost.getSlug())
                .setContent(blogPost.getContent())
                .setCreatedBy(blogPost.getUser().getUsername())
                .setCreatedOn(OffsetDateTime.now())
                .setId(blogPost.getId())
                .setIsDeleted(blogPost.getIsDeleted());
        // Recursively convert and set the subblogs
        List<BlogPostDto> subBlogDtos = convertToDto(blogPost.getSubblogs());
        dto.setSubBlogs(subBlogDtos);
        return dto;
    }
}
