package com.example.springbootproj.controller;


import com.example.springbootproj.dao.ArchiveDAO;
import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.service.BookService;
import com.example.springbootproj.service.ReaderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ArchiveDAO archiveDAO;
    @Autowired
    private BookService bookService;

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
        bookService.saveBook(book);

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

    @RequestMapping("/showArchive")
    public String showArchive(Model model) {

        return "allArchive";
    }
}
