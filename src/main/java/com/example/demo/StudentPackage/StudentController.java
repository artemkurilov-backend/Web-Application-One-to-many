package com.example.demo.StudentPackage;

import com.example.demo.BookPackage.BookService;
import com.example.demo.StudentPackage.Student;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/StudentController")
public class StudentController {
    private final StudentService service;
    private final BookService bookService;

    public StudentController(StudentService service, BookService bookService) {
        this.service = service;
        this.bookService = bookService;
    }


    @GetMapping("/ListOfStudents")
    public String listOfUsers(Model model,
                              @RequestParam(defaultValue = "0") int page){

        int pageSize = 20;

        Page<Student> studentPage =
                service.getAllStudents(page, pageSize);

        model.addAttribute("students",
                studentPage.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages",
                studentPage.getTotalPages());

        return "getAll";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return "redirect:/StudentController/ListOfStudents";
    }
    @GetMapping("/formAddStudent")
    public String openFormAddStudent(Model model){
        model.addAttribute("student", new Student());

        return "form";
    }
    @PostMapping("/formAddStudent/new")
    public String createStudent(@ModelAttribute Student student, Model model){

        String phone = student.getPhonenumber();
        String firstname = student.getFirstName();
        String lastname = student.getLastName();

        if(phone == null || firstname == null || lastname == null || !phone.matches("\\d+")) {
            return "errorPageSTUDENT";
        }

        service.saveStudent(student);
        return "redirect:/StudentController/ListOfStudents";
    }
    @GetMapping("/TheListOfBooksHeTook/{id}")
    public String listOfBooksHeTook(Model model,
                                    @PathVariable Long id){

        Student student = service.findStudentById(id);

        model.addAttribute("student", student);
        model.addAttribute("borrows", student.getBorrows());
        return "StudentTookBook";
    }
    @GetMapping("/takeBookForm/{studentId}")
    public String formForTakeBook(@PathVariable Long studentId, Model model){

        model.addAttribute("studentId", studentId);
        model.addAttribute("books", bookService.getAllBooks());

        return "takeBook";
    }
}
