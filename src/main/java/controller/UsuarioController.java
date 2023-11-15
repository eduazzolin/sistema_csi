package controller;

import java.sql.SQLException;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioController {
   UsuarioDAO usuarioDAO = new UsuarioDAO();

   /**
    * Este método converte a senha do usuário para hash, gera as chaves públicas e privadas e manda tudo para o banco.
    * O banco tenta retornar o objeto usuário com id preenchido
    * @param usuario
    * @return usuario com id no caso de sucesso ou sem id em caso de falha
    */
   public UsuarioVO cadastrarUsuario(UsuarioVO usuario) {
      String senhaHash = SegurancaController.toHash(usuario.getSenha());
      usuario.setSenha(senhaHash);
      byte[][] chaves = SegurancaController.generateKey();
      usuario.setChavePrivada(chaves[0]);
      usuario.setChavePublica(chaves[1]);
      return usuarioDAO.inserirUsuario(usuario);
   }

   /**
    * Este método converte a senha do usuário para hash e busca o usuário no banco pela senha e nome de usuário
    * @param usuario
    * @return usuario com id no caso de sucesso ou sem id em caso de falha
    */
   public UsuarioVO efetuarLogin(UsuarioVO usuario) {
      String senhaHash = SegurancaController.toHash(usuario.getSenha());
      usuario.setSenha(senhaHash);
      return usuarioDAO.login(usuario);
   }

   /***
    * Busca um usuário no banco de dados através do id
    * @param id
    * @return UsuarioVO com id preenchido se encontrado ou zerado se não encontrado
    */
   public UsuarioVO buscarUsuarioPorId(int id) {
      UsuarioVO usuario = new UsuarioVO();
      try {
         usuario = usuarioDAO.buscarUsuarioPorId(id);
      } catch (SQLException e) {
      }
      return usuario;
   }

}
