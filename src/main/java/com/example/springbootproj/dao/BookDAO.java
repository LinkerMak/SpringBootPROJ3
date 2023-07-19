package com.example.springbootproj.dao;


import com.example.springbootproj.entity.Book;

import java.util.List;

public interface BookDAO {

    public List<Book> getAllBooks();

    public void saveBook(Book book);

    public Book getBook(int id);

    public void deleteBook(int id);
}
