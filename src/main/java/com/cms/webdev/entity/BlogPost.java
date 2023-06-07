package com.cms.webdev.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "blog_post")
@Where(clause = "is_deleted=false")
@ToString
public class BlogPost extends BaseEntity<Long> {
    private String title;
    private String content;

    private String slug;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogPost> subblogs=new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private BlogPost parent;

    private Long lft;
    private Long rgt;
    private Long mpttValue;
}
