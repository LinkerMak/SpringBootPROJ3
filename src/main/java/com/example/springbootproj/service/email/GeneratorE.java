package com.example.springbootproj.service.email;

import com.example.springbootproj.dao.BookDAO;
import com.example.springbootproj.dao.Form1DAO;
import com.example.springbootproj.dao.Form1DAOImpl;
import com.example.springbootproj.dao.ReaderDAO;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.entity.Task;
import com.example.springbootproj.service.Form1Service;
import com.example.springbootproj.utils.ReaderBookForm;
import com.example.springbootproj.utils.TaskStatus;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class GeneratorE {

    @Autowired
    private DefaultEmailService defaultEmailService;
    @Autowired
    private Form1DAO form1DAO;

    @Autowired
    private ReaderDAO readerDAO;

    @Autowired
    private BookDAO bookDAO;

    @Async("emailScheduler")
    @Scheduled(fixedDelay = 60000)
    public void scheduledEmail() throws MessagingException {
        System.out.println("emailSheduler is started");

        List<Form1> forms = getForms();

        if (Objects.isNull(forms)) {
            System.out.println("Overdue forms not found)");
            return;
        }

        System.out.println("Overdue forms is found)");
        generate(forms);
    }

    @Transactional
    public List<Form1> getForms() {
        List<Form1> forms= form1DAO.getOverdueForms();
        if(forms.size() == 0) {
            return null;
        }

        return forms;
    }

    @Transactional
    public void generate(List<Form1> forms) throws MessagingException {

        List<ReaderBookForm> rbfList = new ArrayList<>();

        List<Integer> idReaders = new ArrayList<>();
        for(Form1 form : forms) {
            if(!idReaders.contains(form.getReader_id())) {
                idReaders.add(form.getReader_id());
            }

            System.out.println(form);
        }

        for(int idReader : idReaders) {
            Reader reader = readerDAO.getReader(idReader);
            for(int i = 0; i < forms.size(); i++) {
                if(forms.get(i).getReader_id() == idReader) {
                    rbfList.add(new ReaderBookForm(reader,bookDAO.getBook(forms.get(i).getBook_id()), forms.get(i)));
                }
            }

            System.out.println(idReader);
        }


        for(int idReader : idReaders) {
            Reader reader = readerDAO.getReader(idReader);
           /* EmailContext eContext = new EmailContext();
            eContext.setFrom("Библиотека");
            eContext.setTo("Уважаемый" + reader.getName());
            eContext.setSubject("Задолженность");
            eContext.setEmail(reader.getEmail());*/
            String str = "";
            for(ReaderBookForm rbf : rbfList) {
                if(rbf.getReader().getId() == idReader) {
                    int merge = LocalDate.now().getDayOfYear() - LocalDate.parse(rbf.getForm1().getDate_return()).getDayOfYear();
                    str += "Задолженность по книге:" + rbf.getBook().getName() +
                            " состовляет " + merge * 30 + " рублей.\n";
                }
            }
           /* Map<String, Object> map = new HashMap<>();
            map.put("firstName",reader.getName());
            map.put("verifyURL",reader.getName());
            eContext.setContext(map);
            eContext.setAttachment(str);
*/

            defaultEmailService.sendSimpleEmail(reader.getEmail(),"linkermak@mail.ru","Задолженность",str);
            //defaultEmailService.sendSimpleEmailWithAttachment(eContext);
        }

        System.out.println("generated is ok( send email)");
    }


}
