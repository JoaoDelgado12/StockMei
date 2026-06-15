package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Dao.FiltroDashboardDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FiltroDashboardModel;

@WebServlet("api/estoque/filtro")
public class FIltroDashboardController extends HttpServlet{
	
	protected void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException{
		
		FiltroDashboardModel filtro = new FiltroDashboardModel(request.getParameter("nome"),
				request.getParameter("tipo"),
				request.getParameter("data"));
		
		FiltroDashboardDAO filtroDAO = new FiltroDashboardDAO();
		
		if(!filtroDAO.filtrar(filtro)){
			response.sendRedirect(request.getContextPath() + "/pages/controleestoque.html");
		}
		
		
		
	}
}

