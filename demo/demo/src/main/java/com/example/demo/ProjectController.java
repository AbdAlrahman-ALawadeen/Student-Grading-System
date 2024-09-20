package com.example.demo;

import com.example.DAO.DAO;
import com.example.Models.Admin;
import com.example.Models.Course;
import com.example.Models.Student;
import com.example.Models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProjectController {
    DAO dao;

    @Autowired
    public ProjectController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/authenticate")
    public String index(@RequestParam String email, @RequestParam String password, @RequestParam String type, Model model) {
        long ID = -1;
        String message = "";

        switch (type) {
            case "Admin" -> {
                List<Admin> admins = dao.getADMINS();
                for (Admin admin : admins) {
                    if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                        ID = admin.getId();
                    }
                }
            }
            case "Teacher" -> {
                List<Teacher> teachers = dao.getTeachers();
                for (Teacher teacher : teachers) {
                    if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                        ID = teacher.getId();
                    }
                }
            }
            case "Student" -> {
                List<Student> students = dao.getStudents();
                for (Student student : students) {
                    if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                        ID = student.getId();
                    }
                }
            }
        }

        if (ID == -1L) {
            message = "Invalid email or password.";
            model.addAttribute("errorMessage", message);
            return "index";
        } else {
            message = String.valueOf(ID);
            model.addAttribute("ID", message);
            model.addAttribute("type", type);
            return "ControlPage";
        }
    }

    @GetMapping("/addingStudent")
    public String addStudent(){
        return "addStudent";
    }

    @GetMapping("/addingTeacher")
    public String addTeacher(){
        return "addTeacher";
    }

    @GetMapping("/addingCourse")
    public String addCourse(){
        return "addCourse";
    }

    @GetMapping("/addingGrade")
    public String addGrade(){
        return "addGrade";
    }

    @GetMapping("/addingAdmin")
    public String addAdmin(){
        return "addAdmin";
    }

    @GetMapping("/listAdmins")
    public String listAdmins(){
        return "listAdmins";
    }

    @GetMapping("/listTeachers")
    public String listTeachers(){
        return "listTeachers";
    }

    @GetMapping("/listStudents")
    public String listStudents(){
        return "listStudents";
    }

    @GetMapping("/listCourses")
    public String listCourses(){
        return "listCourses";
    }

    @GetMapping("/listGrades")
    public String listGrades(){
        return "listGrades";
    }

    @GetMapping("/listTeacherCourses")
    public String listTeacherCourses(){
        return "listTeacherCourses";
    }

    @PostMapping("/handleChoice")
    public String handleUserChoice(@RequestParam String ID, @RequestParam String choice, @RequestParam String type, Model model) {
        model.addAttribute("ID", ID);

        return switch (type) {
            case "Admin" -> handleAdminChoice(choice, model);
            case "Teacher" -> handleTeacherChoice(choice, model);
            case "Student" -> handleStudentChoice(choice, model);
            default -> "index";
        };
    }


    private String handleAdminChoice(String choice, Model model) {

        return switch (choice) {
            case "showAdmins" -> {
                model.addAttribute("admins", dao.getADMINS());
                yield "listAdmins";
            }
            case "showCourses" -> {
                List<Course> courses = dao.getCourses();
                model.addAttribute("courses", courses);
                yield "listCourses";
            }
            case "showStudents" -> {
                model.addAttribute("students", dao.getStudents());
                yield "listStudents";
            }
            case "addAdmin" -> "addAdmin";
            case "addTeacher" -> "addTeacher";
            case "addStudent" -> "addStudent";
            case "addCourse" -> "addCourse";
            default -> "ControlPage";
        };
    }

    private String handleTeacherChoice(String choice, Model model) {
        model.addAttribute("ID", model.getAttribute("ID"));
        String tempID = (String) model.getAttribute("ID");
        assert tempID != null;
        Long ID = Long.parseLong(tempID);
        model.addAttribute("ID", ID);

        return switch (choice) {
            case "showCourses" -> {
                model.addAttribute("courses", dao.getTeacherCourses(ID));
                yield "listTeacherCourses";
            }
            case "addGrades" -> "addGrade";
            default -> "ControlPage";
        };
    }

    private String handleStudentChoice(String choice, Model model) {
        model.addAttribute("ID", model.getAttribute("ID"));
        String tempID = (String) model.getAttribute("ID");
        assert tempID != null;
        Long ID = Long.parseLong(tempID);
        model.addAttribute("ID", ID);

        return switch (choice) {
            case "showCourses" -> {
                model.addAttribute("courses", dao.getCourses());
                yield "listCourses";
            }
            case "showGrades" -> {
                model.addAttribute("grades", dao.getGrades(ID));
                yield "listGrades";
            }
            default -> "ControlPage";
        };
    }

}


