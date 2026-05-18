package com.example.demo.BookPackage;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/BookController")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/ListOfBooks")
    public String listOfBooks(Model model,

                              @RequestParam(name = "page", defaultValue = "0")
                              int page,

                              @RequestParam(name = "sort", defaultValue = "default")
                              String sort) {

        int pageSize = 15;

        Page<Book> bookPage =
                service.getAllBooks(page, pageSize, sort);

        model.addAttribute("books",
                bookPage.getContent());

        model.addAttribute("currentPage",
                page);

        model.addAttribute("totalPages",
                bookPage.getTotalPages());

        model.addAttribute("sort",
                sort);

        return "getAllBooks";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        service.deleteBook(id);
        return "redirect:/BookController/ListOfBooks?deleteBook";
    }

    @GetMapping("/formAddBook")
    public String openFormAddBook(Model model) {
        model.addAttribute("book", new Book());
        return "formBook";
    }

    @PostMapping("/formAddBook/new")
    public String createBook(@ModelAttribute Book book) {
        String quantity = book.getQuantity();

        if (quantity == null || !quantity.matches("\\d+")) {
            return "errorPageBOOK";
        }

        service.saveBook(book);
        return "redirect:/BookController/ListOfBooks?successBook";
    }

    @GetMapping("/open/{id}")
    public String openBook(@PathVariable("id") Long id, Model model) {
        Book book = service.openBook(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = service.openBook(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id,
                             @RequestParam String author,
                             @RequestParam String title,
                             @RequestParam String quantity,
                             @RequestParam String content) {

        Book book = service.openBook(id);

        book.setAuthor(author);
        book.setTitle(title);
        book.setQuantity(quantity);
        book.setContent(content);

        service.saveBook(book);

        return "redirect:/BookController/ListOfBooks?editBook";
    }

    @PostMapping("/searchPage")
    public String searchPage(@RequestParam String page) {

        if(!page.matches("\\d+")){
            return "errorPageNoIntBook";
        }

        int pageNumber = Integer.parseInt(page);

        return "redirect:/BookController/ListOfBooks?page=" + (pageNumber - 1);
    }

    @PostMapping("/search")
    public String searchBooks(@RequestParam String keyword,
                              Model model){

        List<Book> books =
                service.searchBooks(keyword);

        model.addAttribute("books", books);

        return "getAllBooks";
    }
}
