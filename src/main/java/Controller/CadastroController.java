
package Controller;

import dao.CadastroUsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.CadastroUsuarioModel;
/**
 *
 * @author Master
 */
@WebServlet("../Cadastro")
public class CadastroController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        CadastroUsuarioModel user = new CadastroUsuarioModel();        
        
        user.setUser(request.getParameter("user"));
        user.setpassword(request.getParameter("password"));
        user.setNome(request.getParameter("nameFirst"));
        user.setSobrenome(request.getParameter("sobrenome"));
        user.setMatricula(request.getParameter("matricula"));
        user.setCpf(request.getParameter("cpf"));
        user.setSexo(request.getParameter("sexo"));
        user.setdtaNascimento(request.getParameter("dtaNascimento")); 
        user.setNome(request.getParameter("telefone"));
        user.setNome(request.getParameter("email"));
        user.setNome(request.getParameter("funcao"));
        user.setNome(request.getParameter("funcao"));
        user.setNome(request.getParameter("usuario"));
        user.setNome(request.getParameter("senha"));
        user.setNome(request.getParameter("cep"));
        user.setNome(request.getParameter("endereco"));
        user.setNome(request.getParameter("numero"));
        user.setNome(request.getParameter("bairro"));
        user.setNome(request.getParameter("cidade"));
        user.setNome(request.getParameter("estado"));
        user.setNome(request.getParameter("complemento"));
        
        CadastroUsersDAO dao = new CadastroUsersDAO();
        
        if(dao.cadastrar(user)){
            response.sendRedirect("pages/dashboard.html");
        }else{
            response.sendRedirect("pages/cadastro.html");
        }
    }
}
