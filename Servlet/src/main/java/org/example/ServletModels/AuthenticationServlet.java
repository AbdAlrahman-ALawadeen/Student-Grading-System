package org.example.ServletModels;


import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Components.Admin;
import org.example.Components.Student;
import org.example.Components.Teacher;
import org.example.DAO.DAO;


@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {

    public AuthenticationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DAO dao = new DAO();
        long ID = -1;
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
            request.setAttribute("errorMessage", "Please enter valid email or password!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else{
            request.setAttribute("ID", ID);
            request.setAttribute("type", type);
            request.getRequestDispatcher("ControlPage.jsp").forward(request, response);
        }

    }
}
