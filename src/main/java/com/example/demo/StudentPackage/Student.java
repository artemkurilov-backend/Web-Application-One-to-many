package com.example.demo.StudentPackage;

import com.example.demo.BorrowPackage.Borrow;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phonenumber;


    @OneToMany(mappedBy = "student")
    private List<Borrow> borrows;


    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String firstName, String lastName, String phonenumber, List<Borrow> borrows) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phonenumber = phonenumber;
        this.borrows = borrows;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
