package com.example.demo.StudentPackage;

import com.example.demo.BorrowPackage.Borrow;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String FirstName;
    private String LastName;
    private String Phonenumber;


    @OneToMany(mappedBy = "student")
    private List<Borrow> borrows;


    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String firstName, String lastName, String phonenumber, List<Borrow> borrows) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        Phonenumber = phonenumber;
        this.borrows = borrows;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
