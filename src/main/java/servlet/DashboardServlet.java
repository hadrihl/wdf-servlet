package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = -849609270688754352L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		if(session == null || session.getAttribute("loggedinuser") == null) {
			resp.sendRedirect("/wdf-servlet/login");
			
		} else {
			req.setAttribute("username", session.getAttribute("loggedinuser"));
			RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
