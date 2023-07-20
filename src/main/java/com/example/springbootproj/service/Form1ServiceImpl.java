package com.example.springbootproj.service;

import com.example.springbootproj.dao.Form1DAO;
import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class Form1ServiceImpl implements Form1Service{

    @Autowired
    private Form1DAO form1DAO;
    @Transactional
    @Override
    public List<Integer> getIdsByReader(int id) {
        return form1DAO.getIdsByReader(id);
    }

    @Override
    @Transactional
    public void deleteBookForReader(Form1 form) {
        form1DAO.deleteBookForReader(form);
    }

    @Override
    @Transactional
    public void addNewBookForReader(Book book, Reader reader) {
        form1DAO.addNewBookForReader(book, reader);
    }

    @Override
    @Transactional
    public Form1 getForm(Book book, Reader reader) {
        return form1DAO.getForm(book,reader);
    }
}
