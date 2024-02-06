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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 4906826824570723663L;
	private UserDao userDaoImpl = new UserDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("loggedinuser") == null) {
			resp.sendRedirect("/wdf-servlet");
			
		} else {
			User user = userDaoImpl.getUserByUsername(session.getAttribute("loggedinuser").toString());
			
			if(user == null) {
				req.setAttribute("errmsg", "Ops! Can't retrieve user's information at the moment.");
				RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
				dispatcher.forward(req, resp);
			} else {
				req.setAttribute("loggedinuser", session.getAttribute("loggedinuser").toString());
				req.setAttribute("user", user);
				RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
				dispatcher.forward(req, resp);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = session.getAttribute("loggedinuser").toString();
		User user = new User();
		user.setUsername(username);
		user.setFirstname(req.getParameter("firstname"));
		user.setLastname(req.getParameter("lastname"));
		user.setCompany(req.getParameter("company"));
		user.setCity(req.getParameter("city"));
		user.setCountry(req.getParameter("country"));
		
		User savedUser = userDaoImpl.updateProfile(user);
		
		if(savedUser == null) {
			req.setAttribute("errmsg", "Can't update profile.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			req.setAttribute("msg", "Profile updated successfully.");
			req.setAttribute("user", user);
			RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
