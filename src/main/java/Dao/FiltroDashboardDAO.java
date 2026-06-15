package Dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.FiltroDashboardModel;

public class FiltroDashboardDAO {
	public boolean filtrar(FiltroDashboardModel filtroModel) {
		String sql = "SELECT p.nome, i.quantidade, p.marca, p.precoVendaUni, i.desconto, i.precoTotal FROM"
				+ "Produto as p INNER JOIN ItensVenda as i on i.produto = p.id "
				+ "WHERE 1 ";
		ArrayList<String> vetorFiltro = new ArrayList<String>();
		try(var con = ConnectionFactory.getConnection()){
			if(!filtroModel.getNome().equals(null)) {
				sql += "and p.nome = ?";
				vetorFiltro.add(filtroModel.getNome());
			}
			
			if(!filtroModel.getMarca().equals(null)) {
				sql += "and p.marca = ?";
				vetorFiltro.add(filtroModel.getNome());
			}
			
			if(!filtroModel.getData().equals(null)) {
				sql += "and i.data = ?";
				vetorFiltro.add(filtroModel.getData());
			}
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
		}catch(Exception e){
			e.getStackTrace();
		}
		return false;
	}
}
