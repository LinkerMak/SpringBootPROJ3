package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Book;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@EnableTransactionManagement
@org.springframework.transaction.annotation.Transactional
public class BookDAOImpl implements BookDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        Session session = entityManager.unwrap(Session.class);

        Query<Book> query = session.createQuery("from Book",Book.class);

        List<Book> books = query.getResultList();

        /*for(Book book : books) {
            System.out.println(book);
        }*/
        return books;
    }
    @Override
    @Transactional
    public void saveBook(Book book) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public Book getBook(int id) {
        Session session = entityManager.unwrap(Session.class);

        Book book = session.get(Book.class,id);

        return book;
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<Book> query = session.createQuery("delete from Book where id =:ID");
        query.setParameter("ID",id);
        query.executeUpdate();
    }
}
