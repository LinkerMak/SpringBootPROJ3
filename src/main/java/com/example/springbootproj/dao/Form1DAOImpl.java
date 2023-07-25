package com.example.springbootproj.dao;

import com.example.springbootproj.entity.ArchiveReaders;
import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@EnableTransactionManagement
public class Form1DAOImpl implements Form1DAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Form1 getForm(Book book, Reader reader) {
        Session session = entityManager.unwrap(Session.class);

        Query<Form1> formQ = session.createQuery("from Form1 where book_id =:bID and reader_id = :rID");
        formQ.setParameter("bID",book.getId());
        formQ.setParameter("rID",reader.getId());

        return formQ.list().get(0);
    }

    @Override
    @Transactional
    public List<Integer> getIdsByReader(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query<Integer> query = session.createQuery("select book_id from Form1 where reader_id =:ID",Integer.class);
        query.setParameter("ID",id);
        query.list();

        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteBookForReader(Form1 form) {
        Session session = entityManager.unwrap(Session.class);

        Query<Form1> query = session.createQuery("delete from Form1 where id = :Id");
        query.setParameter("Id",form.getId());
        query.executeUpdate();

    }

    @Override
    @Transactional
    public void addNewBookForReader(Book book, Reader reader) {
        Session session = entityManager.unwrap(Session.class);

        LocalDate currentDate = LocalDate.now();
        Form1 form1 = new Form1(reader.getId(),book.getId(),currentDate.toString(),currentDate.plusMonths(1).toString(),"-",30,0);

        session.saveOrUpdate(form1);
    }

    @Override
    @Transactional
    public List<Form1> getOverdueForms(int id_reader) {
        Session session = entityManager.unwrap(Session.class);

        Query<Form1> query = session.createQuery("from Form1 where date_fact_return ='-' and CAST(date_return AS date) > CAST(:DAT AS date) and reader_id = :ID", Form1.class);
        LocalDate currentDate = LocalDate.now();
        query.setParameter("DAT",currentDate.toString());
        query.setParameter("ID",id_reader);

        return query.getResultList();
    }
    @Override
    @Transactional
    public List<Form1> getAllFormsByReaderId(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<Form1> arReaders = session.createQuery("from Form1 where reader_id = :ID",Form1.class);
        arReaders.setParameter("ID",id);

        return arReaders.getResultList();
    }

    @Override
    @Transactional
    public List<Form1> getAllFormsByReaderId(int id,String dateFrom,String dateTo) {
        Session session = entityManager.unwrap(Session.class);

        Query<Form1> arReaders = session.createQuery("from Form1 where reader_id = :ID and CAST(date_take AS date) >= CAST(:DATE_FROM AS date) and CAST(date_return AS date) <= CAST(:DATE_TO AS date) ",Form1.class);
        arReaders.setParameter("ID",id);
        arReaders.setParameter("DATE_FROM",dateFrom);
        arReaders.setParameter("DATE_TO",dateTo);

        return arReaders.getResultList();
    }
}
