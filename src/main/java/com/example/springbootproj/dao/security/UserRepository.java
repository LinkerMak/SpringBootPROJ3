package com.example.springbootproj.dao.security;

import com.example.springbootproj.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("from User where id = (select max(id) from User)")
    User getMaxId();

    User getUserById(Long id);

    @Query("update User set reader_id=:idR where id = :idU")
    @Modifying
    @Transactional
    void updateUserById(@Param("idR") int idR,@Param("idU") Long idU);
}