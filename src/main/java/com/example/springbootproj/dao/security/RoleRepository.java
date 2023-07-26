package com.example.springbootproj.dao.security;

import com.example.springbootproj.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}