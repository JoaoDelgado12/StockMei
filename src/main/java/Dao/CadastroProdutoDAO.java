package Dao;

import connection.ConnectionFactory;
import model.CadastroProdutoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class CadastroProdutoDAO {
	private int idPerfil;
	public void verificarOuInserir(String userDadoTarget, String tabela_coluna, Connection con) throws SQLException {
	    // sei que tem que tomar cuidado com INJECTION SQL, mas o valor de fora não entra com o PreparedStatement
		   if (userDadoTarget == null || userDadoTarget.trim().isBlank()) {
		        return;
		    }

		    String sqlSelect = "SELECT 1 FROM " + tabela_coluna + " WHERE " + tabela_coluna + " = ?";
		    try (PreparedStatement stmtSelect = con.prepareStatement(sqlSelect)) {
		        stmtSelect.setString(1, userDadoTarget);
		        try (ResultSet rs = stmtSelect.executeQuery()) {
		            if (rs.next()) {
		                return;
		            }
		        }
		    }

		    String sqlInsert = "INSERT INTO " + tabela_coluna + " (" + tabela_coluna + ") VALUES (?)";
		    try (PreparedStatement stmtInsert = con.prepareStatement(sqlInsert)) {
		        stmtInsert.setString(1, userDadoTarget);
		        stmtInsert.executeUpdate();
		    }
	        
	}
	
	public void cadastrarProduto(CadastroProdutoModel produto, Connection con, String[] listUser) throws SQLException, RuntimeException {		
		String sqlInsert = "INSERT INTO Produto (nome, quantidade, quantidadeMin, precoVendaUni, marca, fornecedor, localArmazenamento) VALUES"
				+ " (?,?,?,?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);){
			
			
			if(produto.getNome() == null || produto.getNome().trim().isBlank()) {
				throw new RuntimeException("Dados Obrigatorio");
			}
			stmt.setString(1,produto.getNome()  );
			
			listUser = new String[] {produto.getMarca(), produto.getFornecedor(), produto.getLocalArmazenamento()};
			
			for(int i=0; i<listUser.length; i++) {
				if(listUser[i] == null || listUser[i].trim().isBlank()) {
					stmt.setNull(i + 5, java.sql.Types.VARCHAR);
				}else {
					stmt.setString(i + 5, listUser[i]);
				}
			}
			
			
			if(produto.getPrecoVendaUnidade() == null || produto.getPrecoVendaUnidade().trim().isBlank()) {
				stmt.setNull(4, java.sql.Types.DECIMAL);
			}else {
				stmt.setString(4, produto.getPrecoVendaUnidade());
			}
			
			listUser = new String[] {produto.getQuantidadeEstoque(), produto.getQuantidadeMinima()};
			
			for(int i=0; i<listUser.length; i++) {
				if(listUser[i] == null || listUser[i].trim().isBlank()) {
					stmt.setNull(i + 2, java.sql.Types.INTEGER);
				}else {
					stmt.setString(i + 2, listUser[i]);
				}
			}
			
			stmt.executeUpdate();
			
			try(ResultSet rs  = stmt.getGeneratedKeys();){
				rs.next();
				this.idPerfil = rs.getInt(1);
			}
		}
	}
	
	public void cadastrarHistEstoque(CadastroProdutoModel produto, Connection con, String[] listUser) throws SQLException {
		String sql = "INSERT INTO HistEstoque (produto, quantidade, dataPrecoCompra, precoCompraUni, precoCompraTotal) VALUES"
				+ "(?,?,?,?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql);){
			if(produto.getQuantidadeCompra() == null || produto.getQuantidadeCompra().trim().isBlank()) {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}else {
				stmt.setString(2, produto.getQuantidadeCompra());
			
			}
			
			
			if(produto.getDtaCompra() == null || produto.getDtaCompra().trim().isBlank()) {
				stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()) );
			}else {
				stmt.setDate(3, java.sql.Date.valueOf( LocalDate.parse(produto.getDtaCompra())));
			
			}
			
			listUser = new String[] {produto.getPrecoCompraUnidade(), produto.getPrecoCompraTotal()};
			
			for(int i=0; i<listUser.length; i++) {
				if(listUser[i] == null || listUser[i].trim().isBlank()) {
					stmt.setNull(i + 4, java.sql.Types.DECIMAL);
				}else {
					stmt.setString(i + 4, listUser[i]);
				}
			}
			
			stmt.setInt(1, this.idPerfil);
			
			stmt.executeUpdate();
		}
		
	}
	
	
	public boolean cadastrar(CadastroProdutoModel produto){
    	Connection con =null;
        try{
        	con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            
            String[] listUser = new String[]{produto.getFornecedor(), "Fornecedor",
            		produto.getMarca(), "Marca"};
            
            for(int i = 0; i<listUser.length; i += 2) {
            	verificarOuInserir(listUser[i], listUser[i+1], con);
            }
            
            
            cadastrarProduto(produto, con, listUser);
            cadastrarHistEstoque(produto, con, listUser);
    		
    	     con.commit();
    	     con.setAutoCommit(true);
    		return true;
        } catch (Exception e){
        	if(con != null) {
        		try {
        			con.rollback();
        		}catch(SQLException ea){
        			ea.printStackTrace();
        		}
        	}
            e.printStackTrace();
            return false;
        }
    }

}


