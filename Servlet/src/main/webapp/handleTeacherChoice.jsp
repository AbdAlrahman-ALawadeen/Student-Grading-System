<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.DAO.DAO" %>
<%@ page import="org.example.Components.Course" %>

<%
    String choice = request.getParameter("choice");
    String tempID = (String) request.getParameter("ID");
    Long ID = Long.parseLong(tempID);

    DAO dao = new DAO();

    if ("addGrades".equals(choice)) {
        request.setAttribute("ID", ID);
        request.setAttribute("type", "Teacher");
        request.getRequestDispatcher("addGrade.jsp").forward(request, response);
    }
    else if ("showCourses".equals(choice)) {
        List<Course> courses = dao.getTeacherCourses(ID);
        request.setAttribute("courses", courses);
        request.setAttribute("coursesSize", courses.size());
        request.getRequestDispatcher("listTeacherCourses.jsp").forward(request, response);
    }
    else if ("signOut".equals(choice)) {
        response.sendRedirect("index.jsp");
    }

    dao.close();
%>
