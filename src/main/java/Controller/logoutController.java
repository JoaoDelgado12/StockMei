package Controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class logoutController extends HttpServlet{
	protected void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath() + "/index.html");
	}
}
