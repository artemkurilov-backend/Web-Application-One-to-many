package com.example.demo.BookPackage;

import com.example.demo.BookPackage.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/BookController")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/ListOfBooks")
    public String listOfBooks(Model model){
        model.addAttribute("books", service.getAllBooks());
        return "/getAllBooks";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteBook(id);
        return "redirect:/BookController/ListOfBooks";
    }
    @GetMapping("/formAddBook")
    public String openFormAddStudent(Model model){
        model.addAttribute("book", new Book());
        return "formBook";
    }
    @PostMapping("/formAddBook/new")
    public String createBook(@ModelAttribute Book book){
        service.saveBook(book);
        return "redirect:/BookController/ListOfBooks";
    }
    @GetMapping("/open/{id}")
    public String openBook(@PathVariable Long id, Model model){
        Book book = service.openBook(id);
        model.addAttribute("book", book);
        return "book";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        Book book = service.editBook(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book){
        service.saveBook(book);
        return "redirect:/BookController/ListOfBooks";
    }

}
