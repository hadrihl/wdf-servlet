package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 715006718620632720L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("loggedinuser") == null) {
			session.invalidate();
			resp.sendRedirect("/wdf-servlet");
			
		} else {
			req.setAttribute("loggedinuser", session.getAttribute("loggedinuser").toString());
			RequestDispatcher dispatcher = req.getRequestDispatcher("update.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
 