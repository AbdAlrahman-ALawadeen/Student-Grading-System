<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.DAO.DAO" %>
<%@ page import="org.example.Components.Course" %>
<%@ page import="org.example.Components.Grade" %>

<%
    String choice = request.getParameter("choice");
    String tempID = (String) request.getParameter("ID");
    Long ID = Long.parseLong(tempID);

    DAO dao = new DAO();

    if ("showCourses".equals(choice)) {
        List<Course> courses = dao.getCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("listCourses.jsp").forward(request, response);
    }
    else if ("showGrades".equals(choice)) {
        List<Grade> grades = dao.getGrades(ID);
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("listGrades.jsp").forward(request, response);
    }
    else if ("signOut".equals(choice)) {
        response.sendRedirect("index.jsp");
    }

    dao.close();
%>
