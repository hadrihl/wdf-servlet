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
import utility.OTPGenerator;

public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4651920540358014074L;
	private UserDao userDaoImpl = new UserDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		
		if(!password.matches(cpassword)) {
			req.setAttribute("errmsg", "Password not match! Please try it again.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req, resp);
			
		} else if(userDaoImpl.emailExists(email)) {
			req.setAttribute("errmsg", "Email/Account already exists! Please login with your account.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req, resp);
		
		} else {
			String token = OTPGenerator.generate();
			userDaoImpl.createNewAccount(username, email, password, token);
			
			HttpSession session = req.getSession(true);
			session.setAttribute("email", email);
			resp.sendRedirect("/wdf-servlet/confirm");
		}
	}

}