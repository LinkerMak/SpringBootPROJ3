package com.example.springbootproj.controller;


import com.example.springbootproj.dao.ArchiveBooksDAO;
import com.example.springbootproj.dao.ArchiveReadersDAO;
import com.example.springbootproj.dao.TaskDAO;
import com.example.springbootproj.entity.*;
import com.example.springbootproj.service.BookService;
import com.example.springbootproj.service.Form1Service;
import com.example.springbootproj.service.ReaderService;
import com.example.springbootproj.utils.TaskStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
public class MyController {

    @Autowired
    private Form1Service form1Service;
    @Autowired
    private ArchiveBooksDAO archiveBooksDAO;

    @Autowired
    private ArchiveReadersDAO archiveReadersDAO;
    @Autowired
    private BookService bookService;

    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private ReaderService readerService;

    @RequestMapping("/allBooks")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("allBooks",books);
        return "allBooks";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("bookId") int id,Model model) {
        Book book = bookService.getBook(id);

        model.addAttribute("book",book);

        return "bookInfo";
    }

    @RequestMapping("/saveBook")
    public String saveBook(@ModelAttribute("book")Book book) {
        System.out.println(book);
        System.out.println(bookService.isExists(book.getId()));
        if (bookService.isExists(book.getId()) == false) {
            bookService.saveBook(book);
            Book bookMax = bookService.getBook(bookService.getBookMaxId());
            archiveBooksDAO.addBookInArchive(bookMax);

        }
        else {
            bookService.saveBook(book);
        }
        return "redirect:/allBooks";
    }

    @RequestMapping("/addNewBook")
    public String addNewBook(Model model) {
        Book book = new Book();

        model.addAttribute("book",book);

        return "bookInfo";
    }

    @RequestMapping("/showInformationBookById")
    public String showBookInformation(@RequestParam("bookId") int id,Model model,HttpServletRequest request) {

        Book book = bookService.getBook(id);

        request.setAttribute("name",book.getName());
        request.setAttribute("author",book.getAuthor());

        model.addAttribute("book", book);

        return "bookDI";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") int id) {

        bookService.deleteBook(id);

        return "redirect:/allBooks";
    }
    @RequestMapping("/readers")
    public String showAllReaders(Model model) {
        List<Reader> readers = readerService.getAllReaders();

        model.addAttribute("allReaders",readers);

        return "allReaders";
    }

    @RequestMapping("/updateReader")
    public String updateReader(@RequestParam("readerId") int id,Model model) {
        Reader reader = readerService.getReader(id);

        model.addAttribute("reader",reader);

        return "readerInfo";
    }

    @RequestMapping("/saveReader")
    public String saveReader(@ModelAttribute("reader")Reader reader) {
        readerService.saveReader(reader);

        return "redirect:/readers";
    }

    @RequestMapping("/addNewReader")
    public String addNewReader(Model model) {
        Reader reader = new Reader();

        model.addAttribute("reader",reader);

        return "readerInfo";
    }

    @RequestMapping("/showInformationReaderById")
    public String showReaderInformation(@RequestParam("readerId") int id) {

        return "readerDI";
    }

    @RequestMapping("/deleteReader")
    public String deleteReader(@RequestParam("readerId") int id) {

        readerService.deleteReader(id);

        return "redirect:/readers";
    }

    @RequestMapping("/showArchiveBooks")
    public String showArchive(Model model) {

        List<ArchiveBooks> books = archiveBooksDAO.getAllBooks();

        model.addAttribute("arBooks",books);
        return "archiveBooks";
    }


    @RequestMapping("/showArchiveReaders")
    public String showArchiveReaders(Model model) {

        List<Integer> idSReaders = archiveReadersDAO.getIdAllReaders();

        List<ArchiveReaders> readers = new ArrayList<>();

        for(int i = 0; i < idSReaders.size(); i++) {
            readers.add(archiveReadersDAO.getReaderById(idSReaders.get(i)));
            System.out.println(readers.get(i));
        }

        model.addAttribute("arReaders",readers);
        return "archiveReaders";
    }

    @RequestMapping("showBooksByIdReaderArchive")
    public String showBooksByIdReaderArchive(@RequestParam("readerID") int id, Model model) {

        System.out.println("000000000000000000");
        System.out.println(id);
        List<ArchiveReaders> archiveReaders = archiveReadersDAO.getAllBooksByReaderId(id);

        System.out.println(":" + archiveReaders);
        List<ArchiveBookReader> archiveBooksReaders = new ArrayList<>();
        int i = 0;
        for(ArchiveReaders reader : archiveReaders) {
            archiveBooksReaders.add(new ArchiveBookReader(reader,archiveBooksDAO.getBookById(reader.getId_book())));
            System.out.print("*" + archiveBooksReaders.get(i).getReader());
            System.out.print(":");
            System.out.println(archiveBooksReaders.get(i).getBook());

        }

        System.out.println(archiveBooksReaders);
        model.addAttribute("arReadersBooks", archiveBooksReaders);
        return "allBooksByReaderArchive";
    }

    @RequestMapping("/showBooksReaderById")
    public String showBooksByReaderId(@RequestParam("readerId")int id, Model model) {

        List<Integer> booksIds = form1Service.getIdsByReader(id);
        List<Book> books = bookService.getAllBooksByIds(booksIds);
        Reader reader = readerService.getReader(id);

        List<BookForm> bf = new ArrayList<>();
        for(Book book : books) {
            System.out.println(book);
            bf.add(new BookForm(book, form1Service.getForm(book,reader)));
        }

        model.addAttribute("forms" ,bf);
        model.addAttribute("books",books);
        model.addAttribute("reader",reader);
        return "allBooksById";
    }

    @RequestMapping("/addNewBookForReader")
    public String addNewBookForReader(@RequestParam("readerId") int id,Model model) {

        List<Book> books = bookService.getAllFreeBooks();

        model.addAttribute("books",books);
        model.addAttribute("readerId", id);
        return "booksToChoose";
    }

    @RequestMapping("/chooseBook")
    public String chooseBook(@RequestParam("bookId") int id,@RequestParam("readerId") int rid, Model model) {

        Book book = bookService.getBook(id);
        Reader reader = readerService.getReader(rid);

        book.setStatus(true);
        bookService.saveBook(book);
        form1Service.addNewBookForReader(book, reader);

        return "redirect:/showBooksReaderById?readerId=" + rid;
    }

    @RequestMapping("/returnBook")
    public String returnBook(@RequestParam("bookId")int bId,@RequestParam("readerId") int rId, Model model) {

        Book book = bookService.getBook(bId);

        Reader reader = readerService.getReader(rId);

        book.setStatus(false);
        bookService.saveBook(book);
        Form1 form = form1Service.getForm(book,reader);
        System.out.println(form);


        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = LocalDate.parse(form.getDate_return());
        form.setDate_fact_return(currentDate.toString());
        int merge = currentDate.getDayOfYear() - returnDate.getDayOfYear();

        System.out.println(merge);
        if (merge <= 0)  {

            archiveReadersDAO.addBookInArchive(reader,book,form);
            form1Service.deleteBookForReader(form);

            return "redirect:/showBooksReaderById?readerId=" + rId;
        }
        else {
            int sum = merge * 30;

            model.addAttribute("sum", sum);
            model.addAttribute("readerId",rId);
            return "payment";
        }

    }

    @RequestMapping("/showTextInfo")
    public String testInfo(Model model) {
        List<Reader> readers = readerService.getAllReaders();
        List<ReadersTask> rTasks = new ArrayList<>();
        for(Reader reader : readers) {
            rTasks.add(new ReadersTask(reader));
        }
        model.addAttribute("rTasks", rTasks);
        return "readersByReport";
    }


    /*@InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
        dateFormat.setLenient(false);
        *//*for(ReadersTask rt : readersTasks) {
            System.out.println(rt.getReader() + ":" + rt.getFlag());
        }*//*
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }*/

    @RequestMapping("/taskInfo")
    public String taskInfo(@RequestParam("readerId") int id_reader, Model model) {
        Task task = new Task(id_reader, TaskStatus.CREATED.toString());

        model.addAttribute("task",task);
        return "taskInfo";
    }
    @RequestMapping("/addToTask")
    public String addToTask(@ModelAttribute("task") Task tusk)  {

        /*char [] arr = str.toCharArray();
        System.out.println(arr);
        int counter_spaces = 0;
        for(int i = 0; i < arr.length; i++) {

            if(arr[i] == ' ') counter_spaces +=1;

            if (arr[i] == ' ' && counter_spaces == 1) {
                switch(arr[i+1]){
                    case 'J': month = 7;
                    break;
                }
                continue;
            }

            if(arr[i] == ' '  && counter_spaces == 2 ) {
                int c = i + 1;
                String st = "";
                while(arr[c] != ' ') {
                    st += arr[c];
                    c++;
                }
                day = Integer.parseInt(st);
                continue;
            }

            if(arr[i] == ' ' && counter_spaces == 5) {
                int c = i + 1;
                StringBuilder st = new StringBuilder();
                while(st.length() != 4) {
                    st.append(arr[c]);
                    c++;
                }
                year = Integer.parseInt(st.toString());
            }
        }*/
        taskDAO.saveTask(tusk);
        return "redirect:/allBooks";
    }
    /*@RequestMapping("/test")
    public void test(int id_reader) {
        List<Form1> forms = form1Service.getOverdueForms(id_reader);
        for(Form1 form : forms) {
            System.out.println(form);
        }
    }*/
}
