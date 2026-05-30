package Dao;

import connection.ConnectionFactory;
import model.CadastroUsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import util.SenhaHash;

/**
 *
 * @author Master
 */

public class CadastrosUserDAO {
	private int idPerfil;
	
	public void verificarOuInserir(String userDadoTarget, String tabela_coluna, Connection con) throws SQLException {
	    // sei que tem que tomar cuidado com INJECTION SQL, mas o valor de fora não entra com o PreparedStatement
	    String sqlBusca = "SELECT 1 FROM " + tabela_coluna + " WHERE " + tabela_coluna + " = ?";
	    String sqlInsert = "INSERT INTO " + tabela_coluna + " (" + tabela_coluna + ") VALUES (?)";
	    
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
        	con.rollback();
	    	e.printStackTrace();
	    	throw e;
	    }
	}

	
	public void cadastrarPerfilUsuario(CadastroUsuarioModel user, Connection con) throws SQLException {		
		String sqlInsert = "INSERT INTO perfilUsuario (usuario, senha, funcao, grupoPermissao) VALUES"
				+ "(?,?,?,?)";
		
		try(PreparedStatement stmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.setString(1, user.getUsuario());
			String senhaHash = SenhaHash.gerarHash(user.getSenha()); 
			stmt.setString(2, senhaHash);
			stmt.setString(3, user.getFuncao());
			stmt.setString(4, user.getGrupoAcesso());
			
			stmt.executeUpdate();
			
			ResultSet rs  = stmt.getGeneratedKeys();
			rs.next();
			idPerfil = rs.getInt(1);
			
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void cadastrarCepUsuario(CadastroUsuarioModel user, Connection con) throws SQLException {
		String sql = "INSERT INTO cepUsuario (idPerfil, cep, estado, cidade, logradouro, numero, bairro, complemento) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setInt(1, idPerfil);
			stmt.setString(2, user.getCep());
			stmt.setString(3, user.getEstado());
			stmt.setString(4, user.getCidade());
			stmt.setString(5, user.getLogradouro());
			stmt.setInt(6, Integer.parseInt(user.getNumero()) );
			stmt.setString(7, user.getBairro());
			stmt.setString(8, user.getComplemento());
			
			stmt.executeUpdate();
			
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean cadastrarDadosUsuario(CadastroUsuarioModel user, Connection con) {
		
		String sql = "INSERT INTO dadosUsuario (idPerfil, nome, sobrenome, dtaNascimento, sexo, cpf, cep, email, telefone) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			
			stmt.setInt(1, idPerfil);
			stmt.setString(2, user.getNome());
			stmt.setString(3, user.getSobrenome());
			stmt.setDate(4, java.sql.Date.valueOf( LocalDate.parse(user.getDtaNascimento()) ) );
			stmt.setString(5, user.getSexo());
			stmt.setString(6, user.getCpf());
			stmt.setString(7, user.getCep());
			stmt.setString(8, user.getEmail());
			stmt.setString(9, user.getTelefone());
			
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
            
            verificarOuInserir(user.getGrupoAcesso(), "grupoPermissao", con);
            verificarOuInserir(user.getFuncao(), "funcao", con);
    		verificarOuInserir(user.getEstado(), "estado",con);
    		verificarOuInserir(user.getCidade(), "cidade",con);
            
            cadastrarCepUsuario(user, con);
            cadastrarPerfilUsuario(user, con);
    		cadastrarDadosUsuario(user, con);
    		
    	     con.commit();
    	     con.setAutoCommit(true);
    		return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
