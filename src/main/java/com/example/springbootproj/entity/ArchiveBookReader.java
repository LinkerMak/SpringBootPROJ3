package com.example.springbootproj.entity;

public class ArchiveBookReader {

    private ArchiveReaders reader;
    private ArchiveBooks book;

    public ArchiveBookReader() {};
    public ArchiveBookReader(ArchiveReaders reader, ArchiveBooks book) {
        this.reader = reader;
        this.book = book;
    }

    public ArchiveReaders getReader() {
        return reader;
    }

    public void setReader(ArchiveReaders reader) {
        this.reader = reader;
    }

    public ArchiveBooks getBook() {
        return book;
    }

    public void setBook(ArchiveBooks book) {
        this.book = book;
    }
}
