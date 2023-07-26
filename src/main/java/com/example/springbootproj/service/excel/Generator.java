package com.example.springbootproj.service.excel;

import com.example.springbootproj.dao.*;
import com.example.springbootproj.entity.*;
import com.example.springbootproj.utils.ArchiveReaderBook;
import com.example.springbootproj.utils.ReaderBookForm;
import com.example.springbootproj.utils.TaskStatus;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class Generator {

    @Autowired
    private TaskDAO taskDao;

    @Autowired
    private Form1DAO form1DAO;

    @Autowired
    private ReaderDAO readerDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private ArchiveBooksDAO archiveBooksDAO;

    @Autowired
    private ArchiveReadersDAO archiveReadersDAO;

    @Async("taskScheduler")
    @Scheduled(fixedDelay = 60000)
    public void scheduledTask() {
        System.out.println("scheduledTask is started");
        Task task = getTask();
        System.out.println(")))))))))))))))");
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
        try{

            List<Form1> forms = form1DAO.getAllFormsByReaderId(task.getId_reader(),task.getDateFrom(),task.getDateTo());
            Reader reader = readerDAO.getReader(task.getId_reader());
            List<ReaderBookForm> readerBookList = new ArrayList<>();
            System.out.println("1&&&&&&&&&&&&&&&&&&&");
            for(Form1 form : forms) {
                readerBookList.add(new ReaderBookForm(reader,bookDAO.getBook(form.getBook_id()), form));
            }

            List<ArchiveReaders> arForms = archiveReadersDAO.getAllBooksByReaderId(task.getId_reader(),task.getDateFrom(),task.getDateTo());
            List<ArchiveReaderBook> readerBookArchiveList = new ArrayList<>();
            System.out.println("2&&&&&&&&&&&&&&&&&&&");

            for(ArchiveReaders ar: arForms) {
                    readerBookArchiveList.add(new ArchiveReaderBook(ar,bookDAO.getBook(ar.getId_book())));
            }


            ReportExcelStreamWriter writer = new ReportExcelStreamWriter();
            System.out.println("3&&&&&&&&&&&&&&&&&&&");

          /*  Query<MessageData> query = statelessSession.createNamedQuery(MESSAGE_REF_QUERY_NAME, MessageData.class);
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
            }*/

            int count = 0;
            while(count < readerBookList.size()) {
                count++;
                ReaderBookForm rbf = readerBookList.get(count - 1);
                writer.createRow(count,rbf.getForm1(),rbf.getBook(),reader);

                System.out.println("progress {} rows" + count);

            }
            System.out.println("4&&&&&&&&&&&&&&&&&&&");

            int countClone = count;
            count = 0;
            while(count < readerBookArchiveList.size()) {
                count++;
                countClone++;
                ArchiveReaderBook arb = readerBookArchiveList.get(count - 1);
                writer.createRow(countClone,arb.getBook(),arb.getAr(),reader);

                System.out.println("progress {} rows" + count + ":" + countClone);

            }
            System.out.println("5&&&&&&&&&&&&&&&&&&&");

            writer.writeWorkbook();
            task.setStatus(TaskStatus.DONE.toString());
            System.out.println("task {} complete : " + task);
        } catch (Exception e) {
            task.setStatus(TaskStatus.FAIL.toString());
            e.printStackTrace();
            System.out.println("an error occurred with message {}. While executing the task {}:" + e.getMessage() + " : " + task);
        } finally {
            taskDao.saveTask(task);
        }
    }
}
