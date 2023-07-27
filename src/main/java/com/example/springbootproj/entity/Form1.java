package com.example.springbootproj.entity;


import javax.persistence.*;

@Entity
@Table(name = "form1",schema="public")
public class Form1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="id")
    private int id;

    @Column(name = "id_reader")
    private int reader_id;
    @Column(name = "id_book")
    private int book_id;

    @Column(name = "date_take")
    private String date_take;

    @Column(name = "date_return")
    private String date_return;

    @Column(name = "date_fact_return")
    private String date_fact_return;
    @Column(name = "count")
    private int count;
    @Column(name = "merge")
    private int merge;
    public Form1() {}

    public Form1(int reader_id, int book_id, String date_take, String date_return, String date_fact_return, int count, int merge) {
        this.reader_id = reader_id;
        this.book_id = book_id;
        this.date_take = date_take;
        this.date_return = date_return;
        this.date_fact_return = date_fact_return;
        this.count = count;
        this.merge = merge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMerge() {
        return merge;
    }

    public void setMerge(int merge) {
        this.merge = merge;
    }

    @Override
    public String toString() {
        return "Form1{" +
                "id=" + id +
                ", reader_id=" + reader_id +
                ", book_id=" + book_id +
                ", date_take='" + date_take + '\'' +
                ", date_return='" + date_return + '\'' +
                ", date_fact_return='" + date_fact_return + '\'' +
                ", count=" + count +
                ", merge=" + merge +
                '}';
    }
}