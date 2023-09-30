package controller;

import java.sql.SQLException;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioController {
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	/**
	 * Este método converte a senha do usuário para hash e manda para o banco
	 * O banco tenta retornar o objeto usuário com id preenchido
	 * @param usuario
	 * @return usuario com id no caso de sucesso ou sem id em caso de falha
	 */
	public UsuarioVO cadastrarUsuario(UsuarioVO usuario) {
		String senhaHash = SegurancaController.toHash(usuario.getSenha());
		usuario.setSenha(senhaHash);
		return usuarioDAO.inserirUsuario(usuario);
	}
	
	/**
	 * Este método converte a senha do usuário para hash e manda para o banco
	 * O banco faz um select do user e senha e tenta retornar o objeto usuário com id preenchido
	 * @param usuario
	 * @return usuario com id no caso de sucesso ou sem id em caso de falha
	 */
	public UsuarioVO efetuarLogin(UsuarioVO usuario) {
		String senhaHash = SegurancaController.toHash(usuario.getSenha());
		usuario.setSenha(senhaHash);
		return usuarioDAO.login(usuario);
	}

	public UsuarioVO buscarUsuarioPorId(int idDestinatario) {
		UsuarioVO usuario = new UsuarioVO();
		try {
			usuario = usuarioDAO.buscarUsuarioPorId(idDestinatario);
		} catch (SQLException e) {}
		return usuario;
	}

}
