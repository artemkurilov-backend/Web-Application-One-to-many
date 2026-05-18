package com.example.demo.BookPackage;

import com.example.demo.BorrowPackage.BorrowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Book> getAllBooks(int page,
                                  int size,
                                  String sort){

        Pageable pageable;

        if(sort.equals("az")){

            pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("title").ascending()
            );

        }
        else if(sort.equals("za")){

            pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("title").descending()
            );

        }
        else if(sort.equals("new")){

            pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("id").descending()
            );

        }
        else if(sort.equals("old")){

            pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("id").ascending()
            );

        }
        else{

            pageable = PageRequest.of(page, size);

        }

        return bookRepo.findAll(pageable);
    }
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Book openBook(Long id){
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    public List<Book> searchBooks(String keyword){

        return bookRepo
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }
}
