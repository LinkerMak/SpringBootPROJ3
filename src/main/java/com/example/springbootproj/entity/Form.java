package com.example.springbootproj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "form")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "reader_id")
    private int reader_id;
    @Column(name = "book_id")
    private int book_id;

    @Column(name = "date_del")
    private int date_del;

    @Column(name = "date_return")
    private int date_return;

    private int count;

    public Form() {}

    public Form(int reader_id, int book_id, int date_del, int date_return, int count) {
        this.reader_id = reader_id;
        this.book_id = book_id;
        this.date_del = date_del;
        this.date_return = date_return;
        this.count = count;
    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getDate_del() {
        return date_del;
    }

    public void setDate_del(int date_del) {
        this.date_del = date_del;
    }

    public int getDate_return() {
        return date_return;
    }

    public void setDate_return(int date_return) {
        this.date_return = date_return;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
