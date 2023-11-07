package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException {
			// メッセージがからの場合
			if (request.getAttribute("message") == null) {
				request.setAttribute("message", "todoを管理しましょう");
			}
			
			// idを取得して、id毎に閲覧するようにする
			int postId = Integer.parseInt(request.getParameter("id"));
			
			// SQLに接続するための情報
			String url = "jdbc:mysql://localhost/todo";
			String user = "root";
			String password = "";
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		String sql = "SELECT * FROM posts WHERE id = ?";
		try (Connection connection = DriverManager.getConnection (url, user, password);
			PreparedStatement statement = connection.prepareStatement(sql)){
				
				// 一番初めの?に対して
				statement.setInt(1, postId);
				ResultSet results = statement.executeQuery();
				
				while (results.next()) {
					
					String id = results.getString("id");
					request.setAttribute("id", id);
					
					String title = results.getString("title");
					request.setAttribute("title", title);
					
					String content = results.getString("content").replaceAll("¥n","<br>");
					request.setAttribute("content", content);
				}
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.getMessage());
		}
		String view = "/WEB-INF/views/edit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
