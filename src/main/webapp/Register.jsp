<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <style> /* Your CSS styles here */ </style>
</head>
<body>

    <h1>Registration</h1>

    <form action="register" method="post">

        <label for="userId">UserId:</label>
               <input type="text" id="userId" name="userId" required><br><br>

        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName" required><br><br>

        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Register</button>
    </form>

</body>
</html>