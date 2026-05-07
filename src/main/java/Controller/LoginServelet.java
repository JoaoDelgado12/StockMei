package Controller;

import Dao.UserDAO;
import Model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Master
 */

@WebServlet("/login")
public class LoginServelet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
       
        UserModel usermodel = new UserModel(usuario, senha);
        
        UserDAO userdao = new UserDAO();
        
        if(userdao.validarLogin(usermodel) != null){
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usermodel.getUsuario());
            session.setAttribute("grupoPermissao", usermodel.getPermissao());
            
            response.sendRedirect(request.getContextPath() + "/pages/menu.html");
        } else{
            response.sendRedirect(request.getContextPath() + "/index.html");
        }
        
    }
    
}
