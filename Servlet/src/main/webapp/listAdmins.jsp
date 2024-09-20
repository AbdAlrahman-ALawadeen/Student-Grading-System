<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Components.Admin" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Admins</title>
</head>
<body>
    <h1>List of Admins</h1>
    <p>Total number of admins: <%= request.getAttribute("adminsSize") %></p>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        <%
            List<Admin> admins = (List<Admin>) request.getAttribute("admins");
            if (admins != null) {
                for (Admin admin : admins) {
        %>
            <tr>
                <td><%= admin.getId() %></td>
                <td><%= admin.getFirstName() %></td>
                <td><%= admin.getLastName() %></td>
                <td><%= admin.getEmail() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
