package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.vo.MensagemVO;

public class MensagemDAO {

	public MensagemDAO() {
		super();
	}

	// Método para inserir uma mensagem no banco de dados
	public void inserirMensagem(MensagemVO mensagem) throws SQLException {
		String sql = "INSERT INTO MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO) "
				+ "VALUES (?, ?, ?, ?)";
		Connection conexao = Banco.getConnection();

		try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, mensagem.getIdRemetente());
			stmt.setInt(2, mensagem.getIdDestinatario());
			stmt.setString(3, mensagem.getMensagemTitulo());
			stmt.setString(4, mensagem.getMensagemCorpo());
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				mensagem.setIdMensagem(generatedKeys.getInt(1));
			}
		} finally {
			Banco.closeConnection(conexao);
		}
	}

	// Método para buscar mensagens por destinatário
	public List<MensagemVO> buscarMensagensPorDestinatario(int idDestinatario) throws SQLException {
		List<MensagemVO> mensagens = new ArrayList<>();
		String sql = "SELECT ID_MENSAGEM, ID_REMETENTE, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA " + "FROM DB_MENSAGERIA.MENSAGEM "
				+ "WHERE ID_DESTINATARIO = ? order by mensagem_data asc";
		Connection conexao = Banco.getConnection();

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, idDestinatario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int idMensagem = rs.getInt("ID_MENSAGEM");
				int idRemetente = rs.getInt("ID_REMETENTE");
				String mensagemTitulo = rs.getString("MENSAGEM_TITULO");
				String mensagemCorpo = rs.getString("MENSAGEM_CORPO");
				LocalDateTime mensagemData = LocalDateTime.parse(rs.getString("MENSAGEM_DATA"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				MensagemVO mensagem = new MensagemVO(idMensagem, idRemetente, idDestinatario, mensagemTitulo,
						mensagemCorpo, mensagemData);
				mensagens.add(mensagem);
			}
		} finally {
			Banco.closeConnection(conexao);
		}

		return mensagens;
	}

	// Outros métodos para atualizar e excluir mensagens podem ser adicionados aqui
}
