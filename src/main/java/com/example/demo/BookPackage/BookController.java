package com.example.demo.BookPackage;

import com.example.demo.BookPackage.Book;
import org.springframework.data.domain.Page;
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
    public String listOfBooks(Model model,
                              @RequestParam(defaultValue = "0") int page){

        int pageSize = 15;

        Page<Book> bookPage =
                service.getAllBooks(page, pageSize);

        int totalPages = bookPage.getTotalPages();

        int startPage = Math.max(0, page - 4);
        int endPage = Math.min(totalPages - 1, page + 4);

        model.addAttribute("books",
                bookPage.getContent());

        model.addAttribute("currentPage",
                page);

        model.addAttribute("totalPages",
                totalPages);

        model.addAttribute("startPage",
                startPage);

        model.addAttribute("endPage",
                endPage);

        return "getAllBooks";
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
        String quantity = book.getQuantity();
        if(!quantity.matches("\\d+")){
            return"errorPageBOOK";
        }

        service.saveBook(book);
        return "redirect:/BookController/ListOfBooks";
    }
    @GetMapping("/open/{id}")
    public String openBook(@PathVariable Long id, Model model){
        Book book = service.openBook(id);
        model.addAttribute("book", book);
        return "book";
    }
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @RequestParam String author,
                             @RequestParam String title,
                             @RequestParam String quantity,
                             @RequestParam String content){

        Book book = service.openBook(id);

        book.setAuthor(author);
        book.setTitle(title);
        book.setQuantity(quantity);
        book.setContent(content);

        service.saveBook(book);

        return "redirect:/BookController/ListOfBooks";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        Book book = service.openBook(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book){
        service.saveBook(book);
        return "redirect:/BookController/ListOfBooks";
    }

}
