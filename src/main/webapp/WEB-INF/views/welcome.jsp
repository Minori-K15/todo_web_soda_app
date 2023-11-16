<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome</h1>
    <!-- セッションから値を取得 -->
    <p>Welcome, <%= session.getAttribute("username") %>!</p>

    <table>
      <tbody>
        <tr>
          <th>ID</th>
          <td><%= session.getAttribute("id") %></td>
        </tr>
        <tr>
          <th>Username</th>
          <td><%= session.getAttribute("username") %></td>
        </tr>
        <tr>
          <th>Profile</th>
          <td><%= session.getAttribute("profile") %></td>
        </tr>
      </tbody>
    </table>

    <form method="POST" action="logout">
      <input type="submit" value="Logout">
    </form>
  </body>
</html>