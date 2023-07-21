package com.example.springbootproj.dao;


import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;

import java.util.List;

public interface BookDAO {

    public List<Book> getAllBooks();

    public void saveBook(Book book);

    public List<Book> getAllFreeBooks();
    public Book getBook(int id);

    public void deleteBook(int id);
    public List<Book> getAllBooksByIds(List<Integer> ids);

    public boolean isExists(int id);
}
