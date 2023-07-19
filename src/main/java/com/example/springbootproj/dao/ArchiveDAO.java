package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Book;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
@org.springframework.transaction.annotation.Transactional
public class ArchiveDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void addBookInAr(Book book) {
        Session session = entityManager.unwrap(Session.class);

        session.save(book);
    }
}
