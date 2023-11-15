package controller;

import model.dao.MensagemDAO;
import model.dao.UsuarioDAO;
import model.vo.MensagemVO;
import model.vo.UsuarioVO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MensagemController {

   MensagemDAO mensagemDAO = new MensagemDAO();
   UsuarioDAO usuarioDAO = new UsuarioDAO();

   /***
    * Busca todas as mensagens de um usuário no banco de dados.
    * @param idUsuario id do usuário destinatário
    * @return ArrayList<MensagemVO> com todas as mensagens encontradas
    */
   public ArrayList<MensagemVO> buscarMensagensPorDestinatario(int idUsuario) {
      try {
         return (ArrayList<MensagemVO>) mensagemDAO.buscarMensagensPorDestinatario(idUsuario);
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }

   /***
    * Criptografa e registra uma mensagem no banco de dados.
    * @param idRemetente
    * @param usuarioDestinatario
    * @param mensagemTitulo
    * @param mensagemCorpo
    * @throws SQLException
    * @throws NoSuchPaddingException
    * @throws IllegalBlockSizeException
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeySpecException
    * @throws BadPaddingException
    * @throws InvalidKeyException
    */
   public void enviarMensagem(Integer idRemetente, String usuarioDestinatario, String mensagemTitulo, String mensagemCorpo) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
      MensagemVO mensagem = new MensagemVO();
      mensagem.setIdRemetente(idRemetente);
      UsuarioVO destinatario = usuarioDAO.buscarUsuarioPorUsername(usuarioDestinatario);
      mensagem.setIdDestinatario(destinatario.getIdUsuario());

      /* criptografar a mensagem */
      byte[] chavePublicaDestinatario = destinatario.getChavePublica();
      mensagem.setMensagemTitulo(SegurancaController.encrypt(mensagemTitulo, chavePublicaDestinatario));
      mensagem.setMensagemCorpo(SegurancaController.encrypt(mensagemCorpo, chavePublicaDestinatario));

      mensagemDAO.inserirMensagem(mensagem);
   }


}
