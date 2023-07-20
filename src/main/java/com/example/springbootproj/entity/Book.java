package com.example.springbootproj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books",schema = "public")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @Column(name = "pages")
    private int pages;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private boolean status;

    public Book() {}
    public Book(Book book,boolean status) {
        this.author = book.author;
        this.name = book.name;
        this.pages = book.pages;
        this.price = book.price;
        this.status = status;
    }
    public Book(String author, String name, int pages, int price, boolean status) {
        this.author = author;
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
