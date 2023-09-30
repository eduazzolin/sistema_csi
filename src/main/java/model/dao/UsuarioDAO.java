package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	public UsuarioDAO() {
		super();
	}

	public UsuarioVO inserirUsuario(UsuarioVO usuario) {
		
		String sql = "INSERT INTO DB_MENSAGERIA.USUARIO (NOME_USUARIO, SENHA) VALUES (?, ?)";
		Connection conexao = Banco.getConnection();
		
		ResultSet resultado = null;
		try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getSenha());
			statement.execute();
			resultado = statement.getGeneratedKeys();
			if (resultado.next()) {
				usuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			Banco.closeConnection(conexao);
		}
		return usuario;
	}

	public UsuarioVO login(UsuarioVO usuario) {
		String sql = "SELECT ID_USUARIO FROM DB_MENSAGERIA.USUARIO WHERE NOME_USUARIO = ? AND SENHA = ?";
		Connection conexao = Banco.getConnection();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getSenha());
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					usuario.setIdUsuario(resultSet.getInt("ID_USUARIO"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			Banco.closeConnection(conexao);
		}
		return usuario;
	}

	public UsuarioVO buscarUsuarioPorId(int id) throws SQLException {
		String sql = "SELECT * FROM DB_MENSAGERIA.USUARIO WHERE ID_USUARIO = ?";
		Connection conexao = Banco.getConnection();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int idUsuario = resultSet.getInt("ID_USUARIO");
					String nomeUsuario = resultSet.getString("NOME_USUARIO");
					String senha = resultSet.getString("SENHA");
					return new UsuarioVO(idUsuario, nomeUsuario, senha);
				}
			} finally {
				Banco.closeConnection(conexao);
			}
		}
		return null;
	}

	public List<UsuarioVO> listarUsuarios() throws SQLException {
		List<UsuarioVO> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM DB_MENSAGERIA.USUARIO";
		Connection conexao = Banco.getConnection();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int idUsuario = resultSet.getInt("ID_USUARIO");
					String nomeUsuario = resultSet.getString("NOME_USUARIO");
					String senha = resultSet.getString("SENHA");
					usuarios.add(new UsuarioVO(idUsuario, nomeUsuario, senha));
				}
			}
		} finally {
			Banco.closeConnection(conexao);
		}
		return usuarios;
	}

	public void atualizarUsuario(UsuarioVO usuario) throws SQLException {
		String sql = "UPDATE DB_MENSAGERIA.USUARIO SET NOME_USUARIO = ?, SENHA = ? WHERE ID_USUARIO = ?";
		Connection conexao = Banco.getConnection();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getSenha());
			statement.setInt(3, usuario.getIdUsuario());
			statement.executeUpdate();
		} finally {
			Banco.closeConnection(conexao);
		}
	}

	public void excluirUsuario(int id) throws SQLException {
		String sql = "DELETE FROM DB_MENSAGERIA.USUARIO WHERE ID_USUARIO = ?";
		Connection conexao = Banco.getConnection();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} finally {
			Banco.closeConnection(conexao);
		}
	}

}
