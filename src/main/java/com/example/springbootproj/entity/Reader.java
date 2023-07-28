package com.example.springbootproj.entity;

import javax.persistence.*;

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

    @Column(name = "user_id")
    private int user_id;



  /*  @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form",
            joinColumns = {@JoinColumn(name = "id_reader"),
                    @JoinColumn(name="id_book")})
    private Set<Book> books = new HashSet<Book>();

   */ public Reader() {}

    public Reader(int user_id) {
       this.user_id = user_id;
    }
    public Reader(ArchiveReaders arReader) {
       this.email = arReader.getEmail();
       this.number = arReader.getNumber();
       this.name = arReader.getName();
       this.id =arReader.getId_reader();
    }
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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