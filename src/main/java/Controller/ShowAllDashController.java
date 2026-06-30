package Controller;

import java.io.IOException;

import Dao.FiltroDashboardDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FiltroDashboardModel;

@WebServlet("/api/estoque/all")
public class ShowAllDashController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		FiltroDashboardModel dashModel = new FiltroDashboardModel("", "","");
		
		FiltroDashboardDAO dashDao = new FiltroDashboardDAO();
		
		if(dashDao.filtrar(dashModel)) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write(dashDao.getResultado());
			
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
