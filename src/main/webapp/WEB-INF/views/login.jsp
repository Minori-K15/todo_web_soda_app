<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Todoリスト</title>
</head>

<body>
  <h1>ログイン画面</h1>
    <% String message = (String)request.getAttribute("message");  %>
    <p><%= message %></p>

    
   <form action="login" method="get">
      <label for="user_id">ユーザーID: </label><br>
      <input type="id" name="user_id"><br>
<!--       <input type="id" name="user_id" placeholder="ユーザーIDを入力してください"><br> -->
      <label for="password">パスワード: </label><br>
      <input type="password" name="password"><br>
<!--       <input type="password" name="password" placeholder="パスワードを入力してください"><br> -->
      <button type="submit">ログインする</button>
   </form>
  </body>
</html>