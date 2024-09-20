<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Components.Course" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Courses</title>
</head>
<body>
    <h1>List of Your Courses</h1>
    <p>Total number of Courses: <%= ((List)request.getAttribute("courses")).size() %></p>

    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
        </tr>
        <%
            List<Course> courses = (List<Course>) request.getAttribute("courses");
            if (courses != null) {
                for (Course course : courses) {
        %>
            <tr>
                <td><%= course.getId() %></td>
                <td><%= course.getName() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
