<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
  <h1>Login</h1>
   <% String message = (String)request.getAttribute("message");  %>
    <p><%= message %>

    <form method="POST" action="login">
      <label for="username">Username:</label>
      <input type="text" id="username" name="username"><br>
      <label for="password">Password:</label>
      <input type="password" id="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
  <p>or <a href="login_create">Create User</a></p>
</body>
</html>