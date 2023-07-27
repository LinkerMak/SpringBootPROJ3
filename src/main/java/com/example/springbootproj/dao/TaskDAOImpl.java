package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Task;
import com.example.springbootproj.utils.TaskStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@EnableTransactionManagement
public class TaskDAOImpl implements TaskDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Task getTask() {
        Session session = entityManager.unwrap(Session.class);

        System.out.println("((((((((((((((((((((((");
        Query<Task> query = session.createQuery("from Task where status =:STATUS",Task.class);
        query.setParameter("STATUS", TaskStatus.CREATED.toString());

        if(query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    @Transactional
    public void saveTask(Task task) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(task);
    }
}
