package model;

public class FiltroDashboardModel {
	private String nome;
	private String data;
	private String marca;
	
	public FiltroDashboardModel(String nome,String data,String marca) {
		this.nome = nome;
		this.data = data;
		this.marca= marca;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMarca() {
		return this.marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

}
