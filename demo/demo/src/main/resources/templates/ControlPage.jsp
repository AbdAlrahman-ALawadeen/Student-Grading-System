<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String type = (String) request.getAttribute("type");
    String ID = (String) request.getAttribute("ID");  // Receive ID as String from model
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Control Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            width: 400px;
        }
        h1 {
            color: #5d9cec;
        }
        select {
            padding: 10px;
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #5d9cec;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #4a8ad4;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome, <%= type %>!</h1>
    <h2>Select an option:</h2>

    <form action="<%= type.equals("Admin") ? "handleAdminChoice" : (type.equals("Teacher") ? "handleTeacherChoice" : "handleStudentChoice") %>" method="post"> <!-- Updated action -->
        <input type="hidden" name="ID" value="<%= ID %>">
        <input type="hidden" name="type" value="<%= type %>">
        <select name="choice">
            <% if (type.equals("Admin")) { %>
                <option value="showCourses">Show Courses</option>
                <option value="showStudents">Show Students</option>
                <option value="showTeachers">Show Teachers</option>
                <option value="addAdmin">Add Admin</option>
                <option value="showAdmins">Show Admins</option>
                <option value="addTeacher">Add Teacher</option>
                <option value="addStudent">Add Student</option>
                <option value="addCourse">Add Course</option>
                <option value="addStudentToCourse">Add Student to Course</option>
                <option value="signOut">Sign Out</option>
            <% } else if (type.equals("Teacher")) { %>
                <option value="addGrades">Add Grades</option>
                <option value="showCourses">Show Courses</option>
                <option value="signOut">Sign Out</option>
            <% } else if (type.equals("Student")) { %>
                <option value="showCourses">Show Courses</option>
                <option value="showGrades">Show Grades</option>
                <option value="signOut">Sign Out</option>
            <% } %>
        </select>
        <input type="submit" value="Submit">
    </form>
</div>

</body>
</html>
