package com.example.demo.BorrowPackage;

import com.example.demo.BookPackage.Book;
import com.example.demo.BookPackage.BookService;
import com.example.demo.StudentPackage.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("borrow")
public class BorrowController {

    private final BorrowService borrowService;
    private final StudentService studentService;
    private final BookService bookService;

    public BorrowController(BorrowService borrowService, StudentService studentService, BookService bookService) {
        this.borrowService = borrowService;
        this.studentService = studentService;
        this.bookService = bookService;
    }

    @GetMapping("/take/{bookId}/{studentId}")
    public String takeBook(@PathVariable Long bookId,
                           @PathVariable Long studentId){
        Book book = bookService.openBook(bookId);

        if (book == null) {
            return "errorPageBOOKnull";
        }


        String q = book.getQuantity();
        if(q == null || !q.matches("\\d+")){
            return"errorPageBOOK";
        }

        int quantity = Integer.parseInt(q);

        if(quantity < 1){
            return "errorPageNoQuantity";
        }

        Borrow borrow = new Borrow();

        book.setQuantity(String.valueOf(quantity - 1));
        bookService.saveBook(book);

        borrow.setBook(book);
        borrow.setStudent(studentService.findStudentById(studentId));
        borrow.setBorrowDate("today");

        borrowService.save(borrow);

        return "redirect:/StudentController/ListOfStudents";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id){

        Borrow borrow = borrowService.findById(id);

        Book book = borrow.getBook();

        int quantity = Integer.parseInt(book.getQuantity());
        book.setQuantity(String.valueOf(quantity + 1));

        bookService.saveBook(book);

        borrowService.delete(id);

        return "redirect:/StudentController/ListOfStudents";
    }
}
