package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

@WebServlet("/2fa")
public class TwoFAServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7698819285863792344L;
	private UserDao userDaoImpl = new UserDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("username") == null) {
			resp.sendRedirect("/wdf-servlet/login");
			
		} else {
			req.setAttribute("username", session.getAttribute("username").toString());
			req.setAttribute("email", session.getAttribute("email").toString());
			RequestDispatcher dispatcher = req.getRequestDispatcher("2fa.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		User user = userDaoImpl.getUserByToken(token);
		
		if(user == null) {
			req.setAttribute("errmsg", "OTP invalid.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("confirm-fail.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("loggedinuser", user.getUsername());
			resp.sendRedirect("/wdf-servlet/home");
		}
	}

}
