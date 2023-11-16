<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h1>Create</h1>
    <form method="POST" action="login_create">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <label for="password">Profile:</label>
        <textarea id="profile" name="profile"></textarea><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>
