package com.example.demo.BookPackage;

import com.example.demo.StudentPackage.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository
        extends JpaRepository<Book, Long> {


    // Spring Data JPA
    List<Book>
    findByAuthorContainingIgnoreCase(String author);

    List<Book>
    findByTitleContainingIgnoreCase(String title);

    List<Book>
    findByIdIs(Long id);
}
