package com.example.springbootproj.dao.security;

import com.example.springbootproj.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}