<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Components.Course" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Courses</title>
</head>
<body>
    <h1>List of Courses</h1>
    <p>Total number of courses: <%= ((List)request.getAttribute("courses")).size() %></p>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Teacher ID</th>
        </tr>
        <%
            List<Course> courses = (List<Course>) request.getAttribute("courses");
            if (courses != null) {
                for (Course course : courses) {
        %>
            <tr>
                <td><%= course.getId() %></td>
                <td><%= course.getName() %></td>
                <td><%= course.getTeacherID() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
