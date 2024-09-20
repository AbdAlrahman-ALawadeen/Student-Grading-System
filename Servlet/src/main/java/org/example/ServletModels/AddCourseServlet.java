package org.example.ServletModels;

import org.example.DAO.DAO;
import org.example.Components.Course;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Long teacherID = Long.parseLong(request.getParameter("teacherID"));

        Course course = new Course(name, teacherID);

        DAO dao = new DAO();
        dao.addCourse(course);

    }
}
