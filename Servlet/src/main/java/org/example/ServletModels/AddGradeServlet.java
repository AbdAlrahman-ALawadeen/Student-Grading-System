package org.example.ServletModels;



import org.example.DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/addGrade")
public class AddGradeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long studentID = Long.parseLong(request.getParameter("studentID"));
        Long courseID = Long.parseLong(request.getParameter("courseID"));
        int grade = Integer.parseInt(request.getParameter("grade"));

        DAO dao = new DAO();
        dao.addGrade(studentID, courseID, grade);

    }
}
