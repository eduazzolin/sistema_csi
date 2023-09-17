package model.vo;

public class MensagemVO {
	private int idMensagem;
	private int idRemetente;
	private int idDestinatario;
	private String mensagemTitulo;
	private String mensagemCorpo;

	public MensagemVO() {
		super();
	}

	public MensagemVO(int idMensagem, int idRemetente, int idDestinatario, String mensagemTitulo,
			String mensagemCorpo) {
		super();
		this.idMensagem = idMensagem;
		this.idRemetente = idRemetente;
		this.idDestinatario = idDestinatario;
		this.mensagemTitulo = mensagemTitulo;
		this.mensagemCorpo = mensagemCorpo;
	}
	
	public int getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(int idMensagem) {
		this.idMensagem = idMensagem;
	}

	public int getIdRemetente() {
		return idRemetente;
	}

	public void setIdRemetente(int idRemetente) {
		this.idRemetente = idRemetente;
	}

	public int getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public String getMensagemTitulo() {
		return mensagemTitulo;
	}

	public void setMensagemTitulo(String mensagemTitulo) {
		this.mensagemTitulo = mensagemTitulo;
	}

	public String getMensagemCorpo() {
		return mensagemCorpo;
	}

	public void setMensagemCorpo(String mensagemCorpo) {
		this.mensagemCorpo = mensagemCorpo;
	}



}
