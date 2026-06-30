package Controller;

import java.io.IOException;

import java.sql.SQLException;

import Dao.ResumoDashboardDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/estoque/resumo")
public class ResumoDashboardController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest resquest, HttpServletResponse response) throws IOException{		
		
		ResumoDashboardDAO resumo = new ResumoDashboardDAO();
		
		if(resumo.calcular()) {
			response.setContentType("application/json"); //definindo o tipo de dados do conteudo
			
			response.getWriter().write(resumo.getResultado()); //chama o escritor para escrever que seria basicamente enviar o JSON
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
