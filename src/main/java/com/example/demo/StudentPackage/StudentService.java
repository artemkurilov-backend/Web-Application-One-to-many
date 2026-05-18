package com.example.demo.StudentPackage;

import com.example.demo.BorrowPackage.BorrowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Student> getAllStudents(int page,
                                        int size,
                                        String sort){

        Pageable pageable;

        if(sort.equals("az")){

            pageable = PageRequest.of(
                    page, // current page
                    size, // 15
                    Sort.by("firstName").ascending() // ascending
            );

        }
        else if(sort.equals("za")){

            pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("firstName").descending() // descending
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

            pageable = PageRequest.of(page, size); // PageRequest - what page to load, how many elements need to display for user, how to sort data

        }

        return studentRepo.findAll(pageable);
    }
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student findStudentById(Long id){
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    public List<Student> searchStudents(String keyword){

        return studentRepo
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhonenumberContaining(
                        keyword,
                        keyword,
                        keyword
                );
    }

}
