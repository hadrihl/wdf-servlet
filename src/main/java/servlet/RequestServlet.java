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

public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1632062111956553885L;
	private UserDao userDaoImpl = new UserDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("email") == null) {
			resp.sendRedirect("/wdf-servlet");
			
		} else {
			String email = req.getParameter("email");
			
			if(userDaoImpl.emailExists(email)) {
				userDaoImpl.requestOTP(email);
				req.setAttribute("email", email);
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("confirm.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
