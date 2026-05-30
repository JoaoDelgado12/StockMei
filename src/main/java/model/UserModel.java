package model;

/**
 *
 * @author Master
 */
public class UserModel{
	private String usuario;
	private String senha;
	private String permissao;
	
	public UserModel(){}
	
	public UserModel(String usuario, String senha){
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
 
	
}
