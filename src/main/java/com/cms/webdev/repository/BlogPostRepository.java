package com.cms.webdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cms.webdev.entity.BlogPost;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT b FROM BlogPost b WHERE b.id=:id AND b.isDeleted=false")
    Optional<BlogPost> findById(long id);
    @Query("SELECT COUNT(b.id) > 0 FROM BlogPost b WHERE b.slug=:slug AND b.isDeleted=false")
    Boolean existsBySlug(String slug);
    @Modifying
    @Query("UPDATE BlogPost b SET b.rgt = b.rgt + 2, b.lft = b.lft + 2 WHERE b.lft > :rgt")
    void updateLeftAndRightBoundaries(long rgt);

    @Query("SELECT b FROM BlogPost b WHERE b.user.id=:userId AND b.parent IS NULL AND b.isDeleted=false ORDER BY b.lft ASC")
    List<BlogPost> findAllOrderedByLeftBoundary(int userId);

    @Query("SELECT b.subblogs FROM BlogPost b WHERE b.id=:blogId AND b.isDeleted=false")
    List<BlogPost> findAllSubBlogsByParentBlogId(long blogId);

    @Modifying
    @Query(value = "WITH RECURSIVE sub_blogs AS (\n" +
            "  SELECT id FROM blog_post WHERE id = :id\n" +
            "  UNION ALL\n" +
            "  SELECT blog_post.id FROM blog_post\n" +
            "  JOIN sub_blogs ON blog_post.parent_id = sub_blogs.id\n" +
            ")\n" +
            "UPDATE blog_post SET is_deleted = true WHERE id IN (SELECT id FROM sub_blogs)",
            nativeQuery = true)
    void deleteBlogPostAndSubBlogs(@Param("id") Long id);

}
