package com.example.demo.BookPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository
        extends JpaRepository<Book, Long> {


    // Spring Data JPA
    List<Book>
    findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title,
            String author
    );
}
