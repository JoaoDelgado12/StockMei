package Dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import model.CadastroUsuarioModel;

/**
 *
 * @author Master
 */
public class CadastrosUserDAO {
    public boolean cadastrar(CadastroUsuarioModel user){
        String sql = "INSERT INTO users" + 
            "(username, pass, nome, sobrenome, matricula, cpf, sexo, email,"
                + " telefone, DtaNascimento, funcao, cep, endereco, numero, bairro, cidade, estado, complemento)" + 
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)";
        try(var con = ConnectionFactory.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getSobrenome());
            stmt.setString(3, user.getMatricula());
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getSexo());
            stmt.setString(6, user.getDtaNascimento());
            stmt.setString(7, user.getEmail());
            stmt.setLong(8, user.getTelefone());
            stmt.setString(9, user.getFuncao());
            stmt.setLong(10, user.getCep());
            stmt.setString(11, user.getEndereco());
            stmt.setString(11, user.getNumero());
            stmt.setString(7, user.getBairro());
            stmt.setString(8, user.getCidade());
            stmt.setString(9, user.getEstado());
            stmt.setString(10, user.getComplemento());
            stmt.setString(12, user.getUsuario());
            stmt.setString(13, user.getSenha());
            
            
            
            stmt.executeUpdate();
            
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
