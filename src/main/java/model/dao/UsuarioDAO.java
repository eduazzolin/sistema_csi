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

   /***
    * Insere um novo usuário no banco de dados
    * @param usuario
    * @return UsuarioVO com id preenchido
    */
   public UsuarioVO inserirUsuario(UsuarioVO usuario) {

      String sql = "INSERT INTO DB_MENSAGERIA.USUARIO (NOME_USUARIO, SENHA, PRIVATE_KEY, PUBLIC_KEY) VALUES (?, ?, ?, ?)";
      Connection conexao = Banco.getConnection();

      ResultSet resultado = null;
      try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
         statement.setString(1, usuario.getNomeUsuario());
         statement.setString(2, usuario.getSenha());
         statement.setBytes(3, usuario.getChavePrivada());
         statement.setBytes(4, usuario.getChavePublica());
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

   /***
    * Busca um usuário no banco de dados através do nome de usuário e senha
    * @param usuario
    * @return UsuarioVO com id preenchido se encontrado ou zerado se não encontrado
    */
   public UsuarioVO login(UsuarioVO usuario) {
      String sql = "SELECT * FROM DB_MENSAGERIA.USUARIO WHERE NOME_USUARIO = ? AND SENHA = ?";
      Connection conexao = Banco.getConnection();
      try (PreparedStatement statement = conexao.prepareStatement(sql)) {
         statement.setString(1, usuario.getNomeUsuario());
         statement.setString(2, usuario.getSenha());
         try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
               usuario.setIdUsuario(resultSet.getInt("ID_USUARIO"));
               usuario.setChavePrivada(resultSet.getBytes("PRIVATE_KEY"));
               usuario.setChavePublica(resultSet.getBytes("PUBLIC_KEY"));
            }
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      } finally {
         Banco.closeConnection(conexao);
      }
      return usuario;
   }

   /***
    * Busca um usuário no banco de dados através do id
    * @param id
    * @return UsuarioVO com id preenchido se encontrado
    * @throws SQLException
    */
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
               byte[] chavePublica = resultSet.getBytes("PUBLIC_KEY");
               byte[] chavePrivada = resultSet.getBytes("PRIVATE_KEY");
               return new UsuarioVO(idUsuario, nomeUsuario, senha, chavePrivada, chavePublica);
            }
         } finally {
            Banco.closeConnection(conexao);
         }
      }
      return null;
   }

   /***
    * Busca usuário pelo username
    * @param usuarioDestinatario
    * @return UsuarioVO completo se encontrado
    * @throws SQLException
    */
   public UsuarioVO buscarUsuarioPorUsername(String usuarioDestinatario) throws SQLException {
      String sql = "SELECT * FROM DB_MENSAGERIA.USUARIO WHERE NOME_USUARIO = ?";
      Connection conexao = Banco.getConnection();
      try (PreparedStatement statement = conexao.prepareStatement(sql)) {
         statement.setString(1, usuarioDestinatario);
         try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
               int idUsuario = resultSet.getInt("ID_USUARIO");
               String nomeUsuario = resultSet.getString("NOME_USUARIO");
               String senha = resultSet.getString("SENHA");
               byte[] chavePublica = resultSet.getBytes("PUBLIC_KEY");
               byte[] chavePrivada = resultSet.getBytes("PRIVATE_KEY");
               return new UsuarioVO(idUsuario, nomeUsuario, senha, chavePrivada, chavePublica);
            } else {
               throw new SQLException("Usuario não encontrado!");
            }
         } finally {
            Banco.closeConnection(conexao);
         }
      }
   }
}
