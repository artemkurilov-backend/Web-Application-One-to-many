package com.example.demo.BookPackage;

import com.example.demo.BorrowPackage.BorrowRepository;
import com.example.demo.BorrowPackage.BorrowService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepo;
    private final BorrowRepository borrowRepo;

    public BookService(BookRepository bookRepo, BorrowRepository borrowRepo) {
        this.bookRepo = bookRepo;
        this.borrowRepo = borrowRepo;
    }


    public Book saveBook(Book book){
        return bookRepo.save(book);
    }
    public void deleteBook(Long id){
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        borrowRepo.deleteAll(book.getBorrows());

        bookRepo.delete(book);
    }

    public Page<Book> getAllBooks(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        return bookRepo.findAll(pageable);
    }
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Book openBook(Long id){
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
    }
    public Book editBook(Long id){
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
    }
    public Book findStudentById(Long id){
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
    }
}
