<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.DAO.DAO" %>
<%@ page import="org.example.Components.Course" %>
<%@ page import="org.example.Components.Student" %>
<%@ page import="org.example.Components.Teacher" %>
<%@ page import="org.example.Components.Admin" %>

<%
    String choice = request.getParameter("choice");
    DAO dao = new DAO();

    if ("showCourses".equals(choice)) {
        List<Course> courses = dao.getCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("listCourses.jsp").forward(request, response);
    }
    else if ("showStudents".equals(choice)) {
        List<Student> students = dao.getStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("listStudents.jsp").forward(request, response);
    }
    else if ("showTeachers".equals(choice)) {
        List<Teacher> teachers = dao.getTeachers();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("listTeachers.jsp").forward(request, response);
    }
    else if ("showAdmins".equals(choice)) {
             List<Admin> admins = dao.getADMINS();
             request.setAttribute("admins", admins);
             request.setAttribute("adminsSize", admins.size());
             request.getRequestDispatcher("listAdmins.jsp").forward(request, response);
    }
    else if ("addAdmin".equals(choice)) {
        request.getRequestDispatcher("addAdmin.jsp").forward(request, response);
    }
    else if ("addTeacher".equals(choice)) {

        request.getRequestDispatcher("addTeacher.jsp").forward(request, response);
    }
    else if ("addStudent".equals(choice)) {
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
    }
    else if ("addCourse".equals(choice)) {
        request.getRequestDispatcher("addCourse.jsp").forward(request, response);
    }
    else if ("addStudentToCourse".equals(choice)) {
        request.getRequestDispatcher("addStudentToCourse.jsp").forward(request, response);
    }
    else if ("signOut".equals(choice)) {
        response.sendRedirect("index.jsp");
    }

    dao.close();
%>
