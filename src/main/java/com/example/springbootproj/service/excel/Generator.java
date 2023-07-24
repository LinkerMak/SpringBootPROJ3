package com.example.springbootproj.service.excel;

import com.example.springbootproj.dao.ArchiveReadersDAO;
import com.example.springbootproj.dao.Form1DAO;
import com.example.springbootproj.dao.TaskDAO;
import com.example.springbootproj.entity.Task;
import com.example.springbootproj.service.excel.ReportExcelStreamWriter;
import com.example.springbootproj.utils.TaskStatus;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class Generator {

    @Autowired
    private TaskDAO taskDao;

    @Autowired
    private Form1DAO form1DAO;

    @Autowired
    private ArchiveReadersDAO archiveReadersDAO;

    @Async("taskScheduler")
    @Scheduled(fixedDelay = 60000)
    public void scheduledTask() {
        System.out.println("scheduledTask is started");
        Task task = getTask();
        if (Objects.isNull(task)) {
            System.out.println("task not found");
            return;
        }
        System.out.println("task found");
        generate(task);
    }

    @Transactional
    public Task getTask() {
        Task task = taskDao.getTask();

        if(Objects.isNull(task)) {
            return null;
        }

        task.setStatus(TaskStatus.IN_PROGRESS.toString());
        taskDao.saveTask(task);
        return task;
    }

    @Transactional
    public void generate(Task task) {
        System.out.println("generating report is started");
        try (
                StatelessSession statelessSession =
        ) {


            ReportExcelStreamWriter writer = new ReportExcelStreamWriter();
            Query<MessageData> query = statelessSession.createNamedQuery(MESSAGE_REF_QUERY_NAME, MessageData.class);
            query.setParameter(1, task.getDateFrom());
            query.setParameter(2, task.getDateTo());
            query.setHint(JPA_SHARED_CACHE_STORE_MODE, null);
            query.setHint(JPA_SHARED_CACHE_RETRIEVE_MODE, null);
            ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
            int index = 0;
            while (results.next()) {
                index++;
                writer.createRow(index, (MessageData) results.get(0));
                if (index % 100000 == 0) {
                    log.info("progress {} rows", index);
                }
            }
            writer.writeWorkbook();
            task.setStatus(DONE.toString());
            log.info("task {} complete", task);
        } catch (Exception e) {
            task.setStatus(FAIL.toString());
            e.printStackTrace();
            log.error("an error occurred with message {}. While executing the task {}", e.getMessage(), task);
        } finally {
            taskRepository.save(task);
        }
    }
}
