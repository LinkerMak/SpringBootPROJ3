package com.example.springbootproj.dao;

import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.entity.Task;

public interface TaskDAO {

    public Task getTask();

    public void saveTask(Task task);
}
