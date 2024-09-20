<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Components.Teacher" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Teachers</title>
</head>
<body>
    <h1>List of Teachers</h1>
    <p>Total number of teachers: <%= ((List)request.getAttribute("teachers")).size() %></p>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        <%
            List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
            if (teachers != null) {
                for (Teacher teacher : teachers) {
        %>
            <tr>
                <td><%= teacher.getId() %></td>
                <td><%= teacher.getFirstName() %></td>
                <td><%= teacher.getLastName() %></td>
                <td><%= teacher.getEmail() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
