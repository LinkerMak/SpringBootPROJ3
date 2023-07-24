package com.example.springbootproj.service;

import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;

import java.util.List;

public interface Form1Service {
    public List<Integer> getIdsByReader(int id);
    public void deleteBookForReader(Form1 form);
    public void addNewBookForReader(Book book, Reader reader);
    public Form1 getForm(Book book, Reader reader);

    public List<Form1> getOverdueForms();
}
