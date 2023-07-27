package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Book;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Book> getAllFreeBooks() {
        Session session = entityManager.unwrap(Session.class);

        Query<Book> query = session.createQuery("from Book where status = false",Book.class);

        return query.getResultList();
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

    @Override
    @Transactional
    public List<Book> getAllBooksByIds(List<Integer> ids) {
        Session session = entityManager.unwrap(Session.class);

        List<Book> books = new ArrayList<>();
        for(int id: ids) {
            books.add(session.get(Book.class,id));
        }

        return books;
    }

    @Override
    @Transactional
    public boolean isExists(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<Boolean> query = session.createQuery("select exists (from Book where id = :ID)",Boolean.class);
        query.setParameter("ID",id);

        return query.getResultList().get(0);
    }

    @Override
    @Transactional
    public Integer getBookMaxId() {
        Session session = entityManager.unwrap(Session.class);

        Query<Integer> query = session.createQuery("select max(id) from Book",Integer.class);

        return query.getResultList().get(0);
    }


}
