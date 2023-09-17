package model.vo;

public class UsuarioVO {
	private int idUsuario;
	private int tipoDeUsuario;
	private String nomeUsuario;
	private String senha;

	public UsuarioVO() {
		super();
	}

	public UsuarioVO(int idUsuario, int tipoDeUsuario, String nomeUsuario, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.tipoDeUsuario = tipoDeUsuario;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(int tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
