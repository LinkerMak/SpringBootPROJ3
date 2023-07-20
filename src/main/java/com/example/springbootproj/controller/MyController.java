package com.example.springbootproj.controller;


import com.example.springbootproj.dao.ArchiveDAO;
import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.service.BookService;
import com.example.springbootproj.service.Form1Service;
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

import java.time.LocalDate;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private Form1Service form1Service;
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


    @RequestMapping("/showBooksReaderById")
    public String showBooksByReaderId(@RequestParam("readerId")int id, Model model) {

        List<Integer> booksIds = form1Service.getIdsByReader(id);
        List<Book> books = bookService.getAllBooksByIds(booksIds);
        Reader reader = readerService.getReader(id);

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

        bookService.saveBook(new Book(book,true));
        form1Service.addNewBookForReader(book, reader);

        return "redirect:/showBooksReaderById?readerId=" + rid;
    }

    @RequestMapping("/returnBook")
    public String returnBook(@RequestParam("bookId")int bId,@RequestParam("readerId") int rId, Model model) {

        Book book = bookService.getBook(bId);
        Reader reader = readerService.getReader(rId);

        bookService.saveBook(new Book(book,false));
        Form1 form = form1Service.getForm(book,reader);

        LocalDate currentDate = LocalDate.now();
        LocalDate formDate = LocalDate.parse(form.getDate_return());
        int merge = currentDate.getDayOfYear() - formDate.getDayOfYear();

        if (merge <= 30)  {
            form1Service.deleteBookForReader(form);
            String url = "redirect:/showBooksReaderById/readerId=" + rId;
            return url;
        }
        else {
            int sum = merge * 30;

            model.addAttribute("sum", sum);
            model.addAttribute("readerId",rId);
            return "payment";
        }

    }
}
