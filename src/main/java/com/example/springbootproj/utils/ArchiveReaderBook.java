package com.example.springbootproj.utils;

import com.example.springbootproj.entity.ArchiveReaders;
import com.example.springbootproj.entity.Book;

public class ArchiveReaderBook {

    private ArchiveReaders ar;

    private Book book;

    public ArchiveReaderBook(ArchiveReaders ar, Book book) {
        this.ar = ar;
        this.book = book;
    }

    public ArchiveReaders getAr() {
        return ar;
    }

    public void setAr(ArchiveReaders ar) {
        this.ar = ar;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "ArchiveReaderBook{" +
                "ar=" + ar +
                ", book=" + book +
                '}';
    }
}
