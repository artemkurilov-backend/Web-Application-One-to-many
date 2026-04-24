package com.example.demo.StudentPackage;

import com.example.demo.BorrowPackage.BorrowRepository;
import com.example.demo.BorrowPackage.BorrowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentsRepository studentRepo;
    private final BorrowRepository borrowRepo;

    public StudentService(StudentsRepository studentRepo, BorrowRepository borrowRepo) {
        this.studentRepo = studentRepo;
        this.borrowRepo = borrowRepo;
    }


    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }
    public void deleteStudent(Long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));

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
