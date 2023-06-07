package com.cms.webdev.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


@Accessors(chain = true)
@Data
@Entity
@Table(name = "users")
@ToString
public class User extends BaseEntity<Integer>{
    private String name;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<BlogPost> blogPost;
}
