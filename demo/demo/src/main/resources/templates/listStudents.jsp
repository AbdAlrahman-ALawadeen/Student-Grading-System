<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Components.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Students</title>
</head>
<body>
    <h1>List of Students</h1>
    <p>Total number of students: <%= ((List)request.getAttribute("students")).size() %></p>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students != null) {
                for (Student student : students) {
        %>
            <tr>
                <td><%= student.getId() %></td>
                <td><%= student.getFirstName() %></td>
                <td><%= student.getLastName() %></td>
                <td><%= student.getEmail() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
