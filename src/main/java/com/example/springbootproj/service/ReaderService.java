package com.example.springbootproj.service;


import com.example.springbootproj.entity.Reader;

import java.util.List;

public interface ReaderService {

    public List<Reader> getAllReaders();

    public void saveReader(Reader reader);

    public Reader getReader(int id);

    public void deleteReader(int id);

    public Integer getReaderMaxId();
}
