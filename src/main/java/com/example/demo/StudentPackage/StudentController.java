package com.example.demo.StudentPackage;

import com.example.demo.BookPackage.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

                              @RequestParam(name = "page", defaultValue = "0")
                              int page,

                              @RequestParam(name = "sort", defaultValue = "default")
                              String sort,
                              @RequestParam(name = "searchBy", defaultValue = "firstName")
                              String searchBy){

        int pageSize = 15;

        Page<Student> studentPage =
                service.getAllStudents(page, pageSize, sort);

        model.addAttribute("students",
                studentPage.getContent());

        model.addAttribute("currentPage",
                page);

        model.addAttribute("totalPages",
                studentPage.getTotalPages());

        model.addAttribute("sort",
                sort);

        return "getAll";
    }

    @GetMapping("/editForm/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Student student = service.findStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute Student student) {
        student.setId(id);
        service.saveStudent(student);
        return "redirect:/StudentController/ListOfStudents?editStudent";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        service.deleteStudent(id);
        return "redirect:/StudentController/ListOfStudents?deleteStudent";
    }

    @GetMapping("/formAddStudent")
    public String openFormAddStudent(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/formAddStudent/new")
    public String createStudent(@ModelAttribute Student student) {

        String phone = student.getPhonenumber();
        String firstname = student.getFirstName();
        String lastname = student.getLastName();

        if (phone == null || firstname == null || lastname == null || !phone.matches("\\d+")) {
            return "errorPageSTUDENT";
        }

        service.saveStudent(student);
        return "redirect:/StudentController/ListOfStudents?success";
    }

    @GetMapping("/TheListOfBooksHeTook/{id}")
    public String listOfBooksHeTook(Model model,
                                    @PathVariable("id") Long id) {

        Student student = service.findStudentById(id);

        model.addAttribute("student", student);
        model.addAttribute("borrows", student.getBorrows());

        return "StudentTookBook";
    }

    @GetMapping("/takeBookForm/{studentId}")
    public String formForTakeBook(@PathVariable("studentId") Long studentId,
                                  Model model) {

        model.addAttribute("studentId", studentId);
        model.addAttribute("books", bookService.getAllBooks());

        return "takeBook";
    }

    @PostMapping("/searchPage")
    public String searchPage(@RequestParam String page) {

        if(!page.matches("\\d+")){
            return "errorPageNoIntStudent";
        }

        int pageNumber = Integer.parseInt(page);

        return "redirect:/StudentController/ListOfStudents?page=" + (pageNumber - 1);
    }

    @PostMapping("/search")
    public String searchStudents(@RequestParam String keyword,
                                 @RequestParam String searchBy,
                                 Model model){

        List<Student> students =
                service.searchStudents(keyword, searchBy);

        model.addAttribute("students", students);

        return "getAll";
    }
}
