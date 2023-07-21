package com.example.springbootproj.dao;

import com.example.springbootproj.entity.ArchiveBooks;
import com.example.springbootproj.entity.Book;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableTransactionManagement
@org.springframework.transaction.annotation.Transactional
public class ArchiveBooksDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void addBookInArchive(Book book) {
        Session session = entityManager.unwrap(Session.class);

        ArchiveBooks oneBook = new ArchiveBooks(book);
        session.save(oneBook);
    }

    @Transactional
    public List<ArchiveBooks> getAllBooks() {
        Session session = entityManager.unwrap(Session.class);

        Query<ArchiveBooks> query= session.createQuery("from ArchiveBooks", ArchiveBooks.class);

        return query.getResultList();
    }

    @Transactional
    public ArchiveBooks getBookById(int id) {

        Session session = entityManager.unwrap(Session.class);

        return session.get(ArchiveBooks.class,id);
    }
}
