package com.example.springbootproj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "archive_books")
public class Archive {

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
}
