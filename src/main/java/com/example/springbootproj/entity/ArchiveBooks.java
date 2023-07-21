package com.example.springbootproj.entity;

import jakarta.persistence.*;

@Entity
@Table(name="archive_books",schema = "public")
public class ArchiveBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_book")
    private int id_book;
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "pages")
    private int pages;

    @Column(name = "price")
    private int price;

    public ArchiveBooks(Book book) {
        this.id_book = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.pages = book.getPages();
        this.price = book.getPrice();
    }

    public ArchiveBooks() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
