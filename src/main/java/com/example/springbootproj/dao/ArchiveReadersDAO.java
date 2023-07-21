package com.example.springbootproj.dao;

import com.example.springbootproj.entity.*;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableTransactionManagement
@org.springframework.transaction.annotation.Transactional
public class ArchiveReadersDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void addBookInArchive(Reader reader, Book book, Form1 form) {
        Session session = entityManager.unwrap(Session.class);

        ArchiveReaders oneReader = new ArchiveReaders(reader,book, form);
        session.save(oneReader);
    }


    @Transactional
    public List<Integer> getIdAllReaders() {
        Session session = entityManager.unwrap(Session.class);

        Query<Integer> query = session.createQuery("select id_reader from ArchiveReaders group by id_reader",Integer.class);

        return query.getResultList();
    }

    @Transactional
    public ArchiveReaders getReaderById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<ArchiveReaders> query = session.createQuery("from ArchiveReaders where id in (select max(id) from ArchiveReaders where id_reader=:ID)",ArchiveReaders.class);
        query.setParameter("ID",id);

        return query.getResultList().get(0);
    }

    @Transactional
    public List<ArchiveReaders> getAllBooksByReaderId(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<ArchiveReaders> arReaders = session.createQuery("from ArchiveReaders where id_reader = :ID",ArchiveReaders.class);
        arReaders.setParameter("ID",id);

        System.out.println("####################");
        return arReaders.getResultList();
    }
}
