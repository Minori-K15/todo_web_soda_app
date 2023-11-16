package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException {
			// メッセージの表示
			if (request.getAttribute("message") == null) {
				// nullの場合のメッセージ
				request.setAttribute("message", "todoを管理しましょう");
			}
			
			// SQLに接続するための情報
			String url = "jdbc:mysql://localhost/todo";
			String user = "root";
			String password = "";
		
			// JDBCドライバをロード
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		
		// sqlコマンドの生成
		String sql = "SELECT * FROM posts";
		
		// DBログイン
		try (Connection connection = DriverManager.getConnection (url, user, password);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery()){
				// 取得したデータをリスト化
				ArrayList<HashMap<String, String>> rows = new
				ArrayList<HashMap<String, String>> ();
				
				while (results.next()) {
					HashMap<String, String> conlums = new
					HashMap<String, String> ();
					
					String id = results.getString("id");
					conlums.put("id", id);
					
					String title = results.getString("title");
					conlums.put("title", title);
					
					rows.add(conlums);
				}
				request.setAttribute("rows", rows);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			request.setAttribute("message", "Exception:" + e.getMessage());
		}
		String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
