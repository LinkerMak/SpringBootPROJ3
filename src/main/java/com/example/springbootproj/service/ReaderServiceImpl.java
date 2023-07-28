package com.example.springbootproj.service;

import com.example.springbootproj.dao.ReaderDAO;
import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderDAO readerDAO;
    @Override
    @Transactional
    public List<Reader> getAllReaders() {
        return readerDAO.getAllReaders();
    }
    @Override
    @Transactional
    public void saveReader(Reader reader) {
        readerDAO.saveReader(reader);
    }

    @Override
    @Transactional
    public Reader getReader(int id) {
        return readerDAO.getReader(id);
    }

    @Override
    @Transactional
    public void deleteReader(int id) {
        readerDAO.deleteReader(id);
    }

    @Override
    @Transactional
    public Integer getReaderMaxId() {
        return readerDAO.getReaderMaxId();
    }
}
