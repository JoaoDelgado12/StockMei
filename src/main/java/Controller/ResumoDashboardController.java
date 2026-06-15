package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import Dao.ResumoDashboardDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("api/estoque/resumo")
public class ResumoDashboardController extends HttpServlet{
	
	protected void doGet(HttpServletResponse response, HttpServletRequest resquest) throws IOException, SQLException{
		response.setContentType("application/json"); //definindo o tipo de dados do conteudo
		response.setCharacterEncoding("UTF-8"); // o tipo de caracter
		
		ResumoDashboardDAO resumo = new ResumoDashboardDAO();
		
		if(resumo.calcular()) {
			response.getWriter().write(resumo.getResultado()); // chama o escritor para escrever que seria basicamente enviar o JSON
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
