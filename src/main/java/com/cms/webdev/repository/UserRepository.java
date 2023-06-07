package com.cms.webdev.repository;

import com.cms.webdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
