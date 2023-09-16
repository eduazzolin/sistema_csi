package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.MensagemVO;

public class MensagemDAO {
	private Connection connection;

	public MensagemDAO(Connection connection) {
		this.connection = connection;
	}

	// Método para inserir uma mensagem no banco de dados
	public void inserirMensagem(MensagemVO mensagem) throws SQLException {
		String sql = "INSERT INTO MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO) "
				+ "VALUES (?, ?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, mensagem.getIdRemetente());
			stmt.setInt(2, mensagem.getIdDestinatario());
			stmt.setString(3, mensagem.getMensagemTitulo());
			stmt.setString(4, mensagem.getMensagemCorpo());
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				mensagem.setIdMensagem(generatedKeys.getInt(1));
			}
		}
	}

	// Método para buscar mensagens por destinatário
	public List<MensagemVO> buscarMensagensPorDestinatario(int idDestinatario) throws SQLException {
		List<MensagemVO> mensagens = new ArrayList<>();
		String sql = "SELECT ID_MENSAGEM, ID_REMETENTE, MENSAGEM_TITULO, MENSAGEM_CORPO " + "FROM MENSAGEM "
				+ "WHERE ID_DESTINATARIO = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, idDestinatario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int idMensagem = rs.getInt("ID_MENSAGEM");
				int idRemetente = rs.getInt("ID_REMETENTE");
				String mensagemTitulo = rs.getString("MENSAGEM_TITULO");
				String mensagemCorpo = rs.getString("MENSAGEM_CORPO");

				MensagemVO mensagem = new MensagemVO(idMensagem, idRemetente, idDestinatario, mensagemTitulo,
						mensagemCorpo);
				mensagens.add(mensagem);
			}
		}

		return mensagens;
	}

	// Outros métodos para atualizar e excluir mensagens podem ser adicionados aqui
}
