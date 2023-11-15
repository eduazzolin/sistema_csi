package model.dao;

import model.vo.MensagemVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

   public MensagemDAO() {
      super();
   }


   /***
    * Insere uma nova mensagem no banco de dados
    * @param mensagem
    * @throws SQLException
    */
   public void inserirMensagem(MensagemVO mensagem) throws SQLException {
      String sql = "INSERT INTO MENSAGEM (ID_REMETENTE, ID_DESTINATARIO, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA) " + "VALUES (?, ?, ?, ?, ?)";
      Connection conexao = Banco.getConnection();

      try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
         stmt.setInt(1, mensagem.getIdRemetente());
         stmt.setInt(2, mensagem.getIdDestinatario());
         stmt.setBytes(3, mensagem.getMensagemTitulo());
         stmt.setBytes(4, mensagem.getMensagemCorpo());
         stmt.setString(5, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
         stmt.executeUpdate();
      } finally {
         Banco.closeConnection(conexao);
      }
   }

   /***
    * Busca todas as mensagens de um determinado id de usuário destinatário
    * @param idDestinatario
    * @return List<MensagemVO>
    * @throws SQLException
    */
   public List<MensagemVO> buscarMensagensPorDestinatario(int idDestinatario) throws SQLException {
      List<MensagemVO> mensagens = new ArrayList<>();
      String sql = "SELECT ID_MENSAGEM, ID_REMETENTE, MENSAGEM_TITULO, MENSAGEM_CORPO, MENSAGEM_DATA " + "FROM DB_MENSAGERIA.MENSAGEM " + "WHERE ID_DESTINATARIO = ? order by mensagem_data asc";
      Connection conexao = Banco.getConnection();

      try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
         stmt.setInt(1, idDestinatario);
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
            int idMensagem = rs.getInt("ID_MENSAGEM");
            int idRemetente = rs.getInt("ID_REMETENTE");
            byte[] mensagemTitulo = rs.getBytes("MENSAGEM_TITULO");
            byte[] mensagemCorpo = rs.getBytes("MENSAGEM_CORPO");
            LocalDateTime mensagemData = LocalDateTime.parse(rs.getString("MENSAGEM_DATA"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            MensagemVO mensagem = new MensagemVO(idMensagem, idRemetente, idDestinatario, mensagemTitulo, mensagemCorpo, mensagemData);
            mensagens.add(mensagem);
         }
      } finally {
         Banco.closeConnection(conexao);
      }

      return mensagens;
   }

}
