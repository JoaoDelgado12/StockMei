
package Controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CadastroProdutoModel;
import Dao.CadastroProdutoDAO;

import java.io.IOException;

@WebServlet("/api/cadastro/produto")
public class CadastroProdutoController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
		CadastroProdutoModel cadastroProduto = new CadastroProdutoModel(
        request.getParameter("nome"),
        request.getParameter("marca"),
        request.getParameter("fornecedor"),
        request.getParameter("quantidadeEstoque"),
        request.getParameter("quantidadeMinima"),
        request.getParameter("precoVendaUnidade"),
        request.getParameter("localArmazenamento"),
        request.getParameter("precoCompraUnidade"),
        request.getParameter("quantidadeCompra"),
        request.getParameter("dtaCompra"),
        request.getParameter("precoCompraTotal")
        );
        
      
		CadastroProdutoDAO cadastroProdutoDao = new CadastroProdutoDAO();
        
        if(cadastroProdutoDao.cadastrar(cadastroProduto)){
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(request.getContextPath() + "/pages/menu.html");
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect(request.getContextPath() + "/pages/menu.html");
        }
    }
}
