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

public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 4510020027046819741L;
	private UserDao myDaoImpl = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("email") == null) {
			resp.sendRedirect("/wdf-servlet");
		} else {
			req.setAttribute("email", session.getAttribute("email").toString());
			RequestDispatcher dispatcher = req.getRequestDispatcher("confirm.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		User user = myDaoImpl.getUserByToken(token);
		
		if(user == null) {
			req.setAttribute("errmsg", "Account not verified.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("confirm-fail.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			HttpSession session = req.getSession();
			session.invalidate();
			req.setAttribute("username", user.getUsername());
			RequestDispatcher dispatcher = req.getRequestDispatcher("confirm-success.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
