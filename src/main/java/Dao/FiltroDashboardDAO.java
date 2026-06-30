package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import connection.ConnectionFactory;
import model.FiltroDashboardModel;

public class FiltroDashboardDAO {
	private String Resultado;
	public boolean filtrar(FiltroDashboardModel filtroModel) {
		String sql = "SELECT p.nome, i.quantidade, p.marca, p.precoVendaUni, i.desconto, i.precoTotal FROM"
				+ " Produto p INNER JOIN ItensVenda i on i.produto = p.id "
				+ " WHERE i.dataVenda >= ? ";
		
		try(var con = ConnectionFactory.getConnection()){
			List<String> listFiltro = new ArrayList<String>();
			
			if(filtroModel.getData() == null || filtroModel.getData().trim().isEmpty()) {
				
				LocalDate dataTarget = LocalDate.now().minusWeeks(1);
				
				filtroModel.setData(dataTarget.toString()); //A saida no LocalDate.now é o mesmo do SQL
			}
			
			listFiltro.add(filtroModel.getData());
			
			if(filtroModel.getNome() != null && !filtroModel.getNome().trim().isEmpty()) {
				sql += "and p.nome = ? ";
				listFiltro.add(filtroModel.getNome());
			}
			
			
			if(filtroModel.getMarca() != null && !filtroModel.getMarca().trim().isEmpty()) {
				sql += "and p.marca = ? ";
				listFiltro.add(filtroModel.getMarca());
			}

			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			for(String i : listFiltro) {
				stmt.setString(listFiltro.indexOf(i)+1, i);
			}

			ResultSet result = stmt.executeQuery();
            
			List<Map<String, Object>> listaDados = new ArrayList<>();
			
			ResultSetMetaData metadata = result.getMetaData();
			int colunaCount = metadata.getColumnCount();
			
			while(result.next()) {
				Map<String, Object> linha = new HashMap<String, Object>();
				for(int i=1; i <= colunaCount; i++) {
					String nomeColuna = metadata.getColumnName(i);
					Object valorColuna = result.getObject(i);
					
					linha.put(nomeColuna, valorColuna);
				}
				listaDados.add(linha);
			}
			

			//Gson do conversor do Json
			Gson gson = new Gson();	
			setResultado(gson.toJson(listaDados));
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	
	public String getResultado() {
		return Resultado;
	}
	public void setResultado(String resultado) {
		Resultado = resultado;
	}
}
