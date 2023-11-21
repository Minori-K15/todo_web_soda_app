package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.HashGenerator;


@WebServlet("/login_create")
public class LoginCreateServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost/todo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// メッセージの表示
		if (request.getAttribute("message") == null) {
			// nullの場合のメッセージ
			request.setAttribute("message", "アカウントを作成してください");
		}
    	String view = "/WEB-INF/views/login_create.jsp";
        request.getRequestDispatcher(view).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String profile = request.getParameter("profile");
		
		// JDBCドライバをロード
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // フォームに入力したパスワードを平文のままにせずハッシュ化する
            String hashedPassword = HashGenerator.generateHash(password);
            String sql = "INSERT INTO users (username, `password` , profile) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                stmt.setString(3, profile);
                stmt.execute();
                
    			// メッセージの表示
    			if (request.getAttribute("message") == null) {
    				// nullの場合のメッセージ
    				request.setAttribute("message", "ユーザー名：" + username + "でアカウントを作成しました");
    			}
                String view = "/WEB-INF/views/index.jsp";
                request.getRequestDispatcher(view).forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database Connection Failed", e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ServletException("Generate hash Failed", e);
        }
    }
}