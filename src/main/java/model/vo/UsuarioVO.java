package model.vo;

public class UsuarioVO {

	/* atributos */
	private int idUsuario;
	private String nomeUsuario;
	private String senha;
	private byte[] chavePrivada;
	private byte[] chavePublica;

	/* construtores */
	public UsuarioVO() {
		super();
	}

	public UsuarioVO(int idUsuario, String nomeUsuario, String senha, byte[] chavePrivada, byte[] chavePublica) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.chavePrivada = chavePrivada;
		this.chavePublica = chavePublica;
	}

	/* getters e setters */
	public byte[] getChavePrivada() {
		return chavePrivada;
	}

	public void setChavePrivada(byte[] chavePrivada) {
		this.chavePrivada = chavePrivada;
	}

	public byte[] getChavePublica() {
		return chavePublica;
	}

	public void setChavePublica(byte[] chavePublica) {
		this.chavePublica = chavePublica;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	/* métodos */
	@Override
	public String toString() {
		return nomeUsuario;
	}

}
