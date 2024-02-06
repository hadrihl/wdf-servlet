package servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = -1643958525044428352L;
	private UserDao userDaoImpl = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("loggedinuser") == null) {
			resp.sendRedirect("/wdf-servlet");
		
		} else {
			List<User> users = userDaoImpl.getAllUsers();
			req.setAttribute("users", users);
			req.setAttribute("loggedinuser", session.getAttribute("loggedinuser").toString());
			RequestDispatcher dispatcher = req.getRequestDispatcher("search.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("keyword");
		
		List<User> users = userDaoImpl.getUsersByKeyword(keyword);
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("search.jsp");
		dispatcher.forward(req, resp);
	}
}
