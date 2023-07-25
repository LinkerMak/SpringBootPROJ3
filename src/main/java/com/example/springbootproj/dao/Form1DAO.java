package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.service.Form1ServiceImpl;

import java.util.List;

public interface Form1DAO {

    public Form1 getForm(Book book, Reader reader);
    public List<Integer> getIdsByReader(int id);

    public void deleteBookForReader(Form1 form);

    public void addNewBookForReader(Book book, Reader reader);

    public List<Form1> getOverdueForms(int id_reader);

    public List<Form1> getAllFormsByReaderId(int id);

    public List<Form1> getAllFormsByReaderId(int id,String dateFrom,String dateTo);

}
