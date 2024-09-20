<!DOCTYPE html>
<html>
<head>
    <title>Add Grade</title>
</head>
<body>
    <h1>Add a Grade</h1>
    <form action="addGrade" method="POST">
        Student ID: <input type="number" name="studentID" required><br>
        Course ID: <input type="number" name="courseID" required><br>
        Grade: <input type="number" name="grade" required><br>
        <input type="submit" value="Add Grade">
    </form>
</body>
</html>
