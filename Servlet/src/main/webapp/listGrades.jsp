<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Components.Grade" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Grades</title>
</head>
<body>
    <h1>List of Grades</h1>
    <p>Total number of grades: <%= ((List)request.getAttribute("grades")).size() %></p>

    <table border="1">
        <tr>
            <th>Student ID</th>
            <th>Course ID</th>
            <th>Grade</th>
        </tr>
        <%
            List<Grade> grades = (List<Grade>) request.getAttribute("grades");
            if (grades != null) {
                for (Grade grade : grades) {
        %>
            <tr>
                <td><%= grade.getStudentID() %></td>
                <td><%= grade.getCourseID() %></td>
                <td><%= grade.getGrade() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
