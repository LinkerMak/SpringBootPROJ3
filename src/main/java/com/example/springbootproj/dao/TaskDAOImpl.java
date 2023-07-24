package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
public class TaskDAOImpl implements TaskDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Task getTask() {
        Session session = entityManager.unwrap(Session.class);

        Query<Task> query = session.createQuery("from Task where status =:STATUS",Task.class);
        query.setParameter("STATUS","created");

        return query.getResultList().get(0);
    }

    @Override
    @Transactional
    public void saveTask(Task task) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(task);
    }
}
