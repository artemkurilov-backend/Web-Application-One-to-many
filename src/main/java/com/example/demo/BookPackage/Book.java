package com.example.demo.BookPackage;

import com.example.demo.BorrowPackage.Borrow;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String author;
    private String title;
    private String quantity;
    @Lob
    private String content;


    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows;


    public Book() {
    }

    public Book(long id) {
        this.id = id;
    }

    public Book(long id, String author, String title, String quantity, String content, List<Borrow> borrows) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.quantity = quantity;
        this.content = content;
        this.borrows = borrows;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
