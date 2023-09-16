package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.UsuarioVO;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserirUsuario(UsuarioVO usuario) throws SQLException {
		String sql = "INSERT INTO DB_MENSAGERIA.USUARIO (TIPO_DE_USUARIO, NOME_USUARIO, SENHA) VALUES (?, ?, ?)";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, usuario.getTipoDeUsuario());
			statement.setString(2, usuario.getNomeUsuario());
			statement.setString(3, usuario.getSenha());
			statement.executeUpdate();
		}
	}

	public UsuarioVO buscarUsuarioPorId(int id) throws SQLException {
		String sql = "SELECT * FROM DB_MENSAGERIA.USUARIO WHERE ID_USUARIO = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int idUsuario = resultSet.getInt("ID_USUARIO");
					int tipoDeUsuario = resultSet.getInt("TIPO_DE_USUARIO");
					String nomeUsuario = resultSet.getString("NOME_USUARIO");
					String senha = resultSet.getString("SENHA");
					return new UsuarioVO(idUsuario, tipoDeUsuario, nomeUsuario, senha);
				}
			}
		}
		return null;
	}

	public List<UsuarioVO> listarUsuarios() throws SQLException {
		List<UsuarioVO> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM DB_MENSAGERIA.USUARIO";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int idUsuario = resultSet.getInt("ID_USUARIO");
					int tipoDeUsuario = resultSet.getInt("TIPO_DE_USUARIO");
					String nomeUsuario = resultSet.getString("NOME_USUARIO");
					String senha = resultSet.getString("SENHA");
					usuarios.add(new UsuarioVO(idUsuario, tipoDeUsuario, nomeUsuario, senha));
				}
			}
		}
		return usuarios;
	}

	public void atualizarUsuario(UsuarioVO usuario) throws SQLException {
		String sql = "UPDATE DB_MENSAGERIA.USUARIO SET TIPO_DE_USUARIO = ?, NOME_USUARIO = ?, SENHA = ? WHERE ID_USUARIO = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, usuario.getTipoDeUsuario());
			statement.setString(2, usuario.getNomeUsuario());
			statement.setString(3, usuario.getSenha());
			statement.setInt(4, usuario.getIdUsuario());
			statement.executeUpdate();
		}
	}

	public void excluirUsuario(int id) throws SQLException {
		String sql = "DELETE FROM DB_MENSAGERIA.USUARIO WHERE ID_USUARIO = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}
}
