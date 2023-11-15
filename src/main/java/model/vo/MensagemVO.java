package model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.UsuarioController;

public class MensagemVO {

   /* atributos */
   private int idMensagem;
   private int idRemetente;
   private int idDestinatario;
   private byte[] mensagemTitulo;
   private byte[] mensagemCorpo;
   private LocalDateTime mensagemData;

   /* construtores */
   public MensagemVO(int idMensagem, int idRemetente, int idDestinatario, byte[] mensagemTitulo, byte[] mensagemCorpo, LocalDateTime mensagemData) {
      this.idMensagem = idMensagem;
      this.idRemetente = idRemetente;
      this.idDestinatario = idDestinatario;
      this.mensagemTitulo = mensagemTitulo;
      this.mensagemCorpo = mensagemCorpo;
      this.mensagemData = mensagemData;
   }

   public MensagemVO() {
      super();
   }

   /* getters e setters */
   public byte[] getMensagemTitulo() {
      return mensagemTitulo;
   }

   public void setMensagemTitulo(byte[] mensagemTitulo) {
      this.mensagemTitulo = mensagemTitulo;
   }

   public byte[] getMensagemCorpo() {
      return mensagemCorpo;
   }

   public void setMensagemCorpo(byte[] mensagemCorpo) {
      this.mensagemCorpo = mensagemCorpo;
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


   /* métodos */
   public String getNomeRemetente() {
      UsuarioController usuarioController = new UsuarioController();
      return usuarioController.buscarUsuarioPorId(idRemetente).getNomeUsuario();
   }

   public String getNomeDestinatario() {
      UsuarioController usuarioController = new UsuarioController();
      return usuarioController.buscarUsuarioPorId(idDestinatario).getNomeUsuario();
   }


}
