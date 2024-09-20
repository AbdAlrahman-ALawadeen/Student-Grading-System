package org.example;

import org.example.DAO.DAO;
import org.example.Models.Admin;
import org.example.Models.Student;
import org.example.Models.Teacher;

import java.util.List;

public class Authentication {
    private static final DAO dao = new DAO();

    public static Long authenticationUser(String email, String password, String type) {
        switch (type) {
            case "1" -> {
                List<Admin> admins = dao.getADMINS();
                for (Admin admin : admins) {
                    if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                        return admin.getId();
                    }
                }
            }
            case "2" -> {
                List<Teacher> teachers = dao.getTeachers();
                for (Teacher teacher : teachers) {
                    if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                        return teacher.getId();
                    }
                }
            }
            case "3" -> {
                List<Student> students = dao.getStudents();
                for (Student student : students) {
                    if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                        return student.getId();
                    }
                }
            }
        }
        return -1L;
    }
}
