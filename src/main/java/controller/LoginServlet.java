package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// 1.セッションの機能をインポート
import jakarta.servlet.http.HttpSession;
import utils.HashGenerator;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost/todo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		// メッセージがからの場合
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "");
		}
		
        String view = "/WEB-INF/views/index.jsp";
        request.getRequestDispatcher(view).forward(request, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // DBに登録されているパスワードはハッシュ化されているためハッシュ化した値で照合する
            String hashedPassword = HashGenerator.generateHash(password);
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String profile = rs.getString("profile");

                    // サーバーの保持するセッションを取得する
                    HttpSession session = req.getSession();
                    // キーと値のペアでセッションに登録する
                    session.setAttribute("id", id);
                    session.setAttribute("username", username);
                    session.setAttribute("profile", profile);
                    res.sendRedirect("welcome");
                } else {
                    String view = "/WEB-INF/views/index.jsp";
                    req.getRequestDispatcher(view).forward(req, res);
                }
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