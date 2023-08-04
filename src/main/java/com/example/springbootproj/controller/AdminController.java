package com.example.springbootproj.controller;

import com.example.springbootproj.dao.ArchiveBooksDAO;
import com.example.springbootproj.dao.ArchiveReadersDAO;
import com.example.springbootproj.dao.TaskDAO;
import com.example.springbootproj.entity.*;
import com.example.springbootproj.entity.security.User;
import com.example.springbootproj.service.BookService;
import com.example.springbootproj.service.Form1Service;
import com.example.springbootproj.service.ReaderService;
import com.example.springbootproj.service.security.UserService;
import com.example.springbootproj.utils.ArchiveBookReader;
import com.example.springbootproj.utils.BookForm;
import com.example.springbootproj.utils.ReadersTask;
import com.example.springbootproj.utils.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private Form1Service form1Service;
    @Autowired
    private ArchiveBooksDAO archiveBooksDAO;

    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private ArchiveReadersDAO archiveReadersDAO;
    @Autowired
    private BookService bookService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String  getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @RequestMapping("/admin/allBooks")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("allBooks",books);
        return "allBooks";
    }

    @RequestMapping("/admin/updateBook")
    public String updateBook(@RequestParam("bookId") int id,Model model) {
        Book book = bookService.getBook(id);

        model.addAttribute("book",book);

        return "bookInfo";
    }

    @RequestMapping("/admin/saveBook")
    public String saveBook(@ModelAttribute("book")Book book) {
        System.out.println(book);
        System.out.println(bookService.isExists(book.getId()));
        if (bookService.isExists(book.getId()) == 0) {
            bookService.saveBook(book);
            Book bookMax = bookService.getBook(bookService.getBookMaxId());
            archiveBooksDAO.addBookInArchive(bookMax);

        }
        else {
            bookService.saveBook(book);
        }
        return "redirect:/admin/allBooks";
    }

    @RequestMapping("/admin/addNewBook")
    public String addNewBook(Model model) {
        Book book = new Book();

        model.addAttribute("book",book);

        return "bookInfo";
    }

    @RequestMapping("/admin/showInformationBookById")
    public String showBookInformation(@RequestParam("bookId") int id, Model model, HttpServletRequest request) {

        Book book = bookService.getBook(id);

        request.setAttribute("name",book.getName());
        request.setAttribute("author",book.getAuthor());

        model.addAttribute("book", book);

        return "bookDI";
    }

    @RequestMapping("/admin/deleteBook")
    public String deleteBook(@RequestParam("bookId") int id) {

        bookService.deleteBook(id);

        return "redirect:/admin/allBooks";
    }
    @RequestMapping("/admin/readers")
    public String showAllReaders(Model model) {
        List<Reader> readers = readerService.getAllReaders();

        model.addAttribute("allReaders",readers);

        return "allReaders";
    }

    @RequestMapping("/admin/updateReader")
    public String updateReader(@RequestParam("readerId") int id,Model model) {
        Reader reader = readerService.getReader(id);

        model.addAttribute("reader",reader);

        return "readerInfo";
    }

    @RequestMapping("/admin/saveReader")
    public String savesReader(@ModelAttribute("reader")Reader reader) {

        if(reader.getUser_id() == 0) {
            readerService.saveReader(reader);
            Integer i = readerService.getReaderMaxId();

            User us = new User(reader.getName(),reader.getName() + "pass",i);
            userService.saveUser(us);

            Reader reader1 = readerService.getReader(i);
            reader1.setUser_id(Math.toIntExact(userService.getMaxUser().getId()));
            return "redirect:/admin/readers";
        }

        readerService.saveReader(reader);

        Integer i = readerService.getReaderMaxId();
        System.out.println(i);
        User user = userService.getUserById((long) reader.getUser_id());
        System.out.println(user);
        user.setReader_id(i);
        System.out.println(user.getReader_id());
        System.out.println(userService.saveUser(user));

        return "redirect:/admin/readers";
    }

    @RequestMapping("/admin/addNewReader")
    public String addNewReader(Model model) {
        Reader reader = new Reader();

        model.addAttribute("reader",reader);

        return "readerInfo";
    }

    @RequestMapping("/admin/showInformationReaderById")
    public String showReaderInformation(@RequestParam("readerId") int id) {

        return "readerDI";
    }

    @RequestMapping("/admin/deleteReader")
    public String deleteReader(@RequestParam("readerId") int id) {

        readerService.deleteReader(id);

        return "redirect:/admin/readers";
    }

    @RequestMapping("/admin/showArchiveBooks")
    public String showArchive(Model model) {

        List<ArchiveBooks> books = archiveBooksDAO.getAllBooks();

        model.addAttribute("arBooks",books);
        return "archiveBooks";
    }


    @RequestMapping("/admin/showArchiveReaders")
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

    @RequestMapping("/admin/showBooksByIdReaderArchive")
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

    @RequestMapping("/admin/showBooksReaderById")
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
    @RequestMapping("/admin/showBooksReaderByNameUser")
    public String showBooksByReaderNameUser(@RequestParam("nameUser")String nameUser, Model model) {

        User user = (User) userService.loadUserByUsername(nameUser);

        int id = user.getReader_id();
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

    @RequestMapping("/admin/addNewBookForReader")
    public String addNewBookForReader(@RequestParam("readerId") int id,Model model) {

        List<Book> books = bookService.getAllFreeBooks();

        model.addAttribute("books",books);
        model.addAttribute("readerId", id);
        return "booksToChoose";
    }

    @RequestMapping("/admin/chooseBook")
    public String chooseBook(@RequestParam("bookId") int id,@RequestParam("readerId") int rid, Model model) {

        System.out.println("1");
        Book book = bookService.getBook(id);
        Reader reader = readerService.getReader(rid);

        book.setStatus(true);
        bookService.saveBook(book);
        form1Service.addNewBookForReader(book, reader);

        return "redirect:/admin/showBooksReaderById?readerId=" + rid;
    }

    @RequestMapping("/admin/returnBook")
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

            return "redirect:/admin/showBooksReaderById?readerId=" + rId;
        }
        else {
            int sum = merge * 30;

            archiveReadersDAO.addBookInArchive(reader,book,form);
            form1Service.deleteBookForReader(form);

            model.addAttribute("sum", sum);
            model.addAttribute("readerId",rId);
            return "payment";
        }

    }

    @RequestMapping("/admin/showTextInfo")
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

    @RequestMapping("/admin/taskInfo")
    public String taskInfo(@RequestParam("readerId") int id_reader, Model model) {
        Task task = new Task(id_reader, TaskStatus.CREATED.toString());

        model.addAttribute("task",task);
        return "taskInfo";
    }
    @RequestMapping("/admin/addToTask")
    public String addToTask(@Valid @ModelAttribute("task") Task tusk, BindingResult bindingResult)  {

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

        if(bindingResult.hasErrors()) {
            return "taskInfo";
        }
        else {
            taskDAO.saveTask(tusk);
            return "redirect:/admin/allBooks";
        }
    }
}