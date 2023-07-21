package com.example.springbootproj.entity;

public class BookForm {

    private Book book;

    private Form1 form;

    public BookForm(Book book, Form1 form) {
        this.book = book;
        this.form = form;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Form1 getForm() {
        return form;
    }

    public void setForm(Form1 form) {
        this.form = form;
    }
}
