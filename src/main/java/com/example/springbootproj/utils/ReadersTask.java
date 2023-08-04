package com.example.springbootproj.utils;

import com.example.springbootproj.entity.Reader;

import java.util.List;

public class ReadersTask {

    private Reader reader;
    private String date_to;

    private String date_from;

    public ReadersTask(Reader reader) {
        this.reader = reader;
    }
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }
}

