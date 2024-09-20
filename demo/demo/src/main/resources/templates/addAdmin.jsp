<!DOCTYPE html>
<html>
<head>
    <title>Add Admin</title>
</head>
<body>
    <h1>Add a New Admin</h1>
    <form action="addAdmin" method="POST">
        First Name: <input type="text" name="firstName" required><br>
        Last Name: <input type="text" name="lastName" required><br>
        Email: <input type="email" name="email" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Add Admin">
    </form>
</body>
</html>
