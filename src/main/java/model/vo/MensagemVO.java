package model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.UsuarioController;

public class MensagemVO {
	private int idMensagem;
	private int idRemetente;
	private int idDestinatario;
	private String mensagemTitulo;
	private String mensagemCorpo;
	private LocalDateTime mensagemData;

	public MensagemVO() {
		super();
	}

	public MensagemVO(int idMensagem, int idRemetente, int idDestinatario, String mensagemTitulo, String mensagemCorpo,
			LocalDateTime mensagemData) {
		super();
		this.idMensagem = idMensagem;
		this.idRemetente = idRemetente;
		this.idDestinatario = idDestinatario;
		this.mensagemTitulo = mensagemTitulo;
		this.mensagemCorpo = mensagemCorpo;
		this.mensagemData = mensagemData;
	}

	public LocalDateTime getMensagemData() {
		return mensagemData;
	}

	public void setMensagemData(LocalDateTime mensagemData) {
		this.mensagemData = mensagemData;
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

	public String getNomeRemetente() {
		UsuarioController usuarioController = new UsuarioController();
		return usuarioController.buscarUsuarioPorId(idRemetente).getNomeUsuario();
	}
	public String getNomeDestinatario() {
		UsuarioController usuarioController = new UsuarioController();
		return usuarioController.buscarUsuarioPorId(idDestinatario).getNomeUsuario();
	}
	
	/**
	 * Este toString é usado na tela da caixa de entrada
	 */
	@Override
	public String toString() {
		String remetente = this.getNomeRemetente();
		return String.format("%-20s    %-12s    %s",
				mensagemTitulo.length() > 20 ? mensagemTitulo.substring(0, 17) + "..." : mensagemTitulo,
				remetente.length() > 12 ? remetente.substring(0, 9) + "..." : remetente,
				mensagemData.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
	}

}
