package com.example.demo.StudentPackage;

import com.example.demo.BookPackage.Book;
import com.example.demo.BookPackage.BookRepository;
import com.example.demo.BorrowPackage.Borrow;
import com.example.demo.BorrowPackage.BorrowRepository;
import com.example.demo.BorrowPackage.BorrowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentsRepository studentRepo;
    private final BorrowRepository borrowRepo;
    private final BookRepository bookRepo;

    public StudentService(StudentsRepository studentRepo, BorrowRepository borrowRepo, BookRepository bookRepo) {
        this.studentRepo = studentRepo;
        this.borrowRepo = borrowRepo;
        this.bookRepo = bookRepo;
    }


    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }
    public void deleteStudent(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));

        for (Borrow borrow : student.getBorrows()) {
            Book book = borrow.getBook();

            String q = book.getQuantity();

            if (q != null && q.matches("\\d+")) {
                int quantity = Integer.parseInt(q);
                book.setQuantity(String.valueOf(quantity + 1));
            }

            bookRepo.save(book);
        }

        borrowRepo.deleteAll(student.getBorrows());

        studentRepo.delete(student);
    }
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
    public Student findStudentById(Long id){
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
    }

}
