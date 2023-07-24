package com.example.springbootproj.entity;

import jakarta.persistence.*;
import org.bouncycastle.crypto.digests.GeneralDigest;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table
public class Task implements Serializable {

    private static final long serialVersionUID = -535665521031411214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_reader")
    private int id_reader;
    @Column(name = "status")
    private String status;

    @Column(name = "date_from")
    private String dateFrom;

    @Column(name = "date_to")
    private String dateTo;

    public Task() {}
    public Task(int id_reader, String status, String dateFrom, String dateTo) {
        this.id_reader = id_reader;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}