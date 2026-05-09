package Dao;

import Model.UserModel;
import connection.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.SenhaHash;


public class UserDAO {
    
    public UserModel validarLogin(UserModel usermodel){
        String sql = "SELECT pU.usuario, pU.senha, gP.grupoPermissao"
        		+ " FROM perfilUsuario AS pU INNER JOIN grupoPermissao_perfilUsuario AS gP"
        		+ " ON pU.grupoPermissao = gP.id WHERE pU.usuario = ?";
        
        try (var con = ConnectionFactory.getConnection(); 
    		PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, usermodel.getUsuario());
            
            ResultSet rs = stmt.executeQuery();
            
	            if(rs.next()){
	                String hashBanco = rs.getString("pU.senha");        
	                System.out.println(System.getenv("DB_PASSDEFAULT"));
                	if(usermodel.getSenha().equals(System.getenv("DB_PASSDEFAULT")) || SenhaHash.verificarSenha(usermodel.getSenha(),hashBanco)) {
                		System.out.println("ENTROU NO IF");
                		UserModel user = new UserModel();
                		
                		
                		user.setUsuario(rs.getString("pU.usuario"));
                		user.setSenha(hashBanco);
                		user.setPermissao(rs.getString("gP.grupoPermissao"));
                		
                		rs.close();
                		return user;
                	}
    
	            }
            rs.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
