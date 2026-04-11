package Dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import Model.CadastroUsuarioModel;

/**
 *
 * @author Master
 */

public class CadastrosUserDAO {
    public boolean cadastrar(CadastroUsuarioModel user){
        String sql = "INSERT INTO cadastro_usuario" + 
            "(nome, sobrenome, matricula, dta_nascimento, sexo, cpf, cep, endereco, estado, bairro "
                + " cidade, numero, complemento, usuario, senha, funcao,email,telefone)" + 
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try(var con = ConnectionFactory.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(14, user.usuario());
            stmt.setString(15, user.senha());
            stmt.setString(1, user.nome());
            stmt.setString(2, user.sobrenome());
            stmt.setString(3, user.matricula());
            stmt.setString(6, user.cpf());
            stmt.setString(5, user.sexo());
            stmt.setString(17, user.email());
            stmt.setString(19, user.telefone());
            stmt.setString(4, user.DtaNascimento());
            stmt.setString(16, user.funcao());
            stmt.setString(7, user.cep());
            stmt.setString(8, user.endereco());
            stmt.setString(12, user.numero());
            stmt.setString(10, user.bairro());
            stmt.setString(11, user.cidade());
            stmt.setString(9, user.estado());
            stmt.setString(13, user.complemento());
            
            stmt.executeUpdate();
            
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
