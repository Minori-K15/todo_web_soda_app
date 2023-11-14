package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static String check_UserId = "Yamada";
	private static String check_Password = "1115";
		
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		// メッセージを表示する
		if(request.getParameter("message") == null ) {
			request.setAttribute("message", "ログインしてください");
		}
		
		// ログイン画面の表示
		String view = "/WEB-INF/views/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
		// 入力されたパスワードとユーザーを取得
//		String user_id =  request.getParameter("user_id");
//		String user_Password =  request.getParameter("password");
		
		// ログインチェック
		String loginStatus = loginCheck(request.getParameter("user_id"), request.getParameter("password"));
		System.out.println("ログインチェックStatus" + loginStatus);
		
		if(loginStatus == "成功") {
			view = "/WEB-INF/views/list.jsp";
			dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}	else {
			System.out.println(loginStatus);
			request.setAttribute("message", "[Failed]" + loginStatus);
		}
	}
	
	// ログインチェックメソッド
	private String loginCheck(String user, String password) {
		
		System.out.println("ログインチェックメソッド");
		System.out.println("UserID : " + user);
		System.out.println("UserPassWord : " + password);
		
		if(check_UserId != user) {
			return "[ERROR]ユーザーIDが間違っています";
		} else if (check_UserId != password) {
			return "[ERROR]パスワードが間違っています";
			} else {
				return "成功";
			}
	}
}
