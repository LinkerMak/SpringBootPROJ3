package com.example.springbootproj.service;


import com.example.springbootproj.dao.BookDAO;
import com.example.springbootproj.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;
    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public List<Book> getAllFreeBooks() {
        return bookDAO.getAllFreeBooks();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }


    @Override
    @Transactional
    public List<Book> getAllBooksByIds(List<Integer> ids) {
        return bookDAO.getAllBooksByIds(ids);
    }

    @Override
    @Transactional
    public int isExists(int id) {
        return bookDAO.isExists(id);
    }

    @Override
    @Transactional
    public Integer getBookMaxId() {
        return bookDAO.getBookMaxId();
    }
}
