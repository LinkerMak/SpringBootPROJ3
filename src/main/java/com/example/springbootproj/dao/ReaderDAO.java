package com.example.springbootproj.dao;


import com.example.springbootproj.entity.Reader;

import java.util.List;

public interface ReaderDAO {

    public List<Reader> getAllReaders();

    public void saveReader(Reader reader);


    public Reader getReader(int id);

    public void deleteReader(int id);

    public Integer getReaderMaxId();
}
