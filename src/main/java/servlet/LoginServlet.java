package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import utility.OTPGenerator;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -154176561953216931L;
	private UserDao userDaoImpl = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("loggedinuser") == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("/wdf-servlet/home");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		User user = new User();
		
		user = userDaoImpl.login(email, password);
		
		if(user == null) {
			req.setAttribute("errmsg", "Wrong email/password. Forgot password?");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			//send otp
			//userDaoImpl.loginOTP(email, OTPGenerator.generate());
			
			HttpSession session = req.getSession(true);
			session.setAttribute("loggedinuser", user.getUsername());
			session.setAttribute("email", email);
			resp.sendRedirect("/wdf-servlet/home");
			
		}
	}
}
