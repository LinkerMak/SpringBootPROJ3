package com.example.springbootproj.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "readers", schema = "public")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String number;

  /*  @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form",
            joinColumns = {@JoinColumn(name = "id_reader"),
                    @JoinColumn(name="id_book")})
    private Set<Book> books = new HashSet<Book>();

   */ public Reader() {}
    public Reader(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

   /* public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}