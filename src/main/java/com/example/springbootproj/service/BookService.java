package com.example.springbootproj.service;


import com.example.springbootproj.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public List<Book> getAllFreeBooks();
    public void saveBook(Book book);

    public Book getBook(int id);

    public void deleteBook(int id);

    public List<Book> getAllBooksByIds(List<Integer> ids);
}
