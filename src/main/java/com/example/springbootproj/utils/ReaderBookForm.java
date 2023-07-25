package com.example.springbootproj.utils;

import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;

public class ReaderBookForm {

    private Reader reader;

    private Book book;

    private Form1 form1;
    public ReaderBookForm(Reader reader, Book book, Form1 form) {
        this.reader = reader;
        this.book = book;
        this.form1 = form;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Form1 getForm1() {
        return form1;
    }

    public void setForm1(Form1 form1) {
        this.form1 = form1;
    }
}
