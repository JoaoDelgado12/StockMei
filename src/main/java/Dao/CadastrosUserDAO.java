package Dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import Model.CadastroUsuarioModel;
import util.SenhaHash;

/**
 *
 * @author Master
 */

public class CadastrosUserDAO {
	
	public void verificarOuInserir(String userDadoTarget, String tabela, String coluna , Connection con) {
	    // sei que tem que tomar cuidado com INJECTION SQL, mas o valor de fora não entra com o PreparedStatement
	    String sqlBusca = "SELECT 1 FROM " + tabela + " WHERE " + coluna + " = ?";
	    String sqlInsert = "INSERT INTO " + tabela + " (" + coluna + ") VALUES (?)";
	    
	    try {
	    	PreparedStatement stmtBusca = con.prepareStatement(sqlBusca);
	        stmtBusca.setString(1, userDadoTarget);
	        ResultSet rs = stmtBusca.executeQuery();
	        if (rs.next()) {
	        	rs.close();
	        	stmtBusca.close();
	        	return ;
	        }
	        

	        PreparedStatement stmtInsert = con.prepareStatement(sqlInsert);
	        stmtInsert.setString(1, userDadoTarget);
	        stmtInsert.executeUpdate();
	        
	        stmtInsert.close();
	        
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

	
	public boolean cadastrarPerfilUsuario(CadastroUsuarioModel user, Connection con) {
		verificarOuInserir(user.getFuncao(), "funcao_perfilUsuario", "funcao", con);
		verificarOuInserir(user.getGrupoAcesso(), "grupoPermissao_perfilUsuario", "grupoPermissao", con);
		
		String sql = "INSERT INTO perfilUsuario (usuario, senha, funcao, grupoPermissao) VALUES"
				+ "(?,?,?,?)";
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setString(1, user.getUsuario());
			String senhaHash = SenhaHash.gerarHash(user.getSenha()); 
			stmt.setString(2, senhaHash);
			stmt.setString(3, user.getFuncao());
			stmt.setString(4, user.getGrupoAcesso());
			
			stmt.executeUpdate();
			
			stmt.close();
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean cadastrarCepUsuario(CadastroUsuarioModel user, Connection con) {
		verificarOuInserir(user.getEstado(), "estado", "estado", con);
		verificarOuInserir(user.getCidade(), "cidade", "cidade", con);
		
		String sql = "INSERT INTO cepUsuario (cep, estado, cidade, logradouro, numero, bairro, complemento) VALUES"
				+ "(?,?,?,?,?,?,?)";
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setString(1, user.getCep());
			stmt.setString(2, user.getEstado());
			stmt.setString(3, user.getCidade());
			stmt.setString(4, user.getLogradouro());
			stmt.setInt(5, Integer.parseInt(user.getNumero()) );
			stmt.setString(6, user.getBairro());
			stmt.setString(7, user.getComplemento());
			
			stmt.executeUpdate();
			
			stmt.close();
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean cadastrarDadosUsuario(CadastroUsuarioModel user, Connection con) {
		
		String sql = "INSERT INTO dadosUsuario (nome, sobrenome, dtaNascimento, sexo, cpf, cep, email, telefone) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getSobrenome());
			stmt.setDate(3, java.sql.Date.valueOf( LocalDate.parse(user.getDtaNascimento()) ) );
			stmt.setString(4, user.getSexo());
			stmt.setString(5, user.getCpf());
			stmt.setString(6, user.getCep());
			stmt.setString(7, user.getEmail());
			stmt.setString(8, user.getTelefone());
			
			stmt.executeUpdate();
			
			stmt.close();
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
    public boolean cadastrar(CadastroUsuarioModel user){
        try(var con = ConnectionFactory.getConnection()){
            con.setAutoCommit(false);
            
            cadastrarCepUsuario(user, con);
            cadastrarPerfilUsuario(user, con);
    		cadastrarDadosUsuario(user, con);
    		
    		return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
