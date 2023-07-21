package com.example.springbootproj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "archive_readers", schema = "public")
public class ArchiveReaders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_reader")
    private int id_reader;

    @Column(name = "id_book")
    private int id_book;
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String number;

    @Column(name = "date_take")
    private String date_take;

    @Column(name = "date_return")
    private String date_return;

    @Column(name = "date_fact_return")
    private String date_fact_return;

    @Column(name = "merge")
    private int merge;

    public ArchiveReaders(Reader reader, Book book, Form1 form) {
        this.id_reader = reader.getId();
        this.id_book = book.getId();
        this.name = reader.getName();
        this.email = reader.getEmail();
        this.number = reader.getNumber();
        this.date_return = form.getDate_return();
        this.date_take = form.getDate_take();
        this.date_fact_return = form.getDate_fact_return();
        this.merge = form.getMerge();
    }

    public ArchiveReaders() {

    }

    public String getDate_take() {
        return date_take;
    }

    public void setDate_take(String date_take) {
        this.date_take = date_take;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getDate_fact_return() {
        return date_fact_return;
    }

    public void setDate_fact_return(String date_fact_return) {
        this.date_fact_return = date_fact_return;
    }

    public int getMerge() {
        return merge;
    }

    public void setMerge(int merge) {
        this.merge = merge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reader() {
        return id_reader;
    }

    public void setId_reader(int id_reader) {
        this.id_reader = id_reader;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
