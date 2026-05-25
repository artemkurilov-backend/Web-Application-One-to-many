package com.example.demo.BorrowPackage;

import com.example.demo.BookPackage.Book;
import com.example.demo.StudentPackage.Student;
import jakarta.persistence.*;

@Entity
public class Borrow {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Book book;
    private String borrowDate;

    public Borrow() {
    }

    public Borrow(long id) {
        this.id = id;
    }

    public Borrow(long id, Student student, Book book, String borrowDate) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
}
