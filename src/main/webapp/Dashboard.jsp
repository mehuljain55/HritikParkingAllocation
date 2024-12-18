<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 50px;
        }

        .dashboard {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 60%;
            margin: 0 auto;
        }

        .dashboard h2 {
            text-align: center;
        }

        .user-info {
            margin-top: 20px;
        }

        .user-info p {
            font-size: 18px;
        }

        .logout-btn {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .logout-btn:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>

    <div class="dashboard">
        <h2>Welcome to the User Dashboard</h2>

        <div class="user-info">
            <p><strong>User ID:</strong> ${user.userId}</p>
            <p><strong>Name:</strong> ${user.name}</p>
            <p><strong>Role:</strong> ${user.role}</p>
        </div>

        <!-- Add a logout button -->
        <form action="logout" method="post">
            <button type="submit" class="logout-btn">Logout</button>
        </form>
    </div>

</body>
</html>
