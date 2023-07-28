package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Reader;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@EnableTransactionManagement
public class ReaderDAOImpl implements ReaderDAO{

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Reader> getAllReaders() {
        Session session = entityManager.unwrap(Session.class);

        Query<Reader> query = session.createQuery("from Reader",Reader.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveReader(Reader reader) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(reader);
    }


    @Override
    @Transactional
    public Reader getReader(int id) {

        Session session = entityManager.unwrap(Session.class);
        Reader reader = session.get(Reader.class,id);

        return reader;
    }

    @Override
    @Transactional
    public void deleteReader(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<Reader> query = session.createQuery("delete from Reader where id =:ID");
        query.setParameter("ID",id);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public Integer getReaderMaxId() {
        Session session = entityManager.unwrap(Session.class);

        Query<Integer> query = session.createQuery("select max(id) from Reader",Integer.class);

        return query.getResultList().get(0);
    }
}
