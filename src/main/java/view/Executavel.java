package view;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.MensagemController;
import controller.SegurancaController;
import controller.UsuarioController;
import model.vo.MensagemVO;
import model.vo.UsuarioVO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Executavel {

   private static Scanner scanner = new Scanner(System.in);
   private static UsuarioController usuarioController = new UsuarioController();
   private static UsuarioVO usuarioLogado = new UsuarioVO();
   private static MensagemController mensagemController = new MensagemController();
   private static String barra = "----------------------";
   private static String negrito = "\u001B[1m";
   private static String reset = "\u001B[0m";
   private static String quebraDePagina = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

   public static void main(String[] args) {

      exibirLogotipo();
      boolean sair = false;

      /* tela de login e cadastro */
      while (usuarioLogado.getIdUsuario() == 0) {
         usuarioLogado = telaLoginCadastro();
      }

      /* tela principal */
      while (!sair) {
         exibirMenuPrincipal();
         int escolha = scanner.nextInt();
         scanner.nextLine();

         switch (escolha) {
            case 1:
               telaCaixaDeEntrada();
               break;
            case 2:
               telaEnviarMensagem();
               break;
            case 9:
               sair = true;
               break;
            default:
               System.out.println("Opção inválida. Tente novamente.");
               break;
         }
      }

      System.out.println("O programa foi encerrado.");
   }


   /***
    * Exibe a tela de envio de mensagem e encaminha para o controller fazer o que tem que fazer.
    */
   private static void telaEnviarMensagem() {
      boolean ok = false;
      while (!ok) {
         try {
            System.out.print("Usuário destinatário: ");
            String usuarioDestinatario = scanner.nextLine();
            System.out.print("Título: ");
            String tituloMensagem = scanner.nextLine();
            System.out.print("Mensagem: ");
            String corpoMensagem = scanner.nextLine();
            mensagemController.enviarMensagem(usuarioLogado.getIdUsuario(), usuarioDestinatario, tituloMensagem, corpoMensagem);
            System.out.println("Mensagem enviada com sucesso!");
            ok = true;
         } catch (Exception e) {
            if (e.getMessage().equals("Data must not be longer than 501 bytes")) {
               System.out.println("A mensagem não deve passar de 500 caracteres!");
            } else {
               System.out.println(e.getMessage());
            }
         }
      }
   }

   /**
    * Exibe as opções do menu principal
    */
   private static void exibirMenuPrincipal() {
      System.out.println(barra);
      System.out.println("1. Caixa de entrada");
      System.out.println("2. Enviar mensagem");
      System.out.println("9. Sair");
      System.out.print("Selecione uma opção: ");
   }

   /**
    * Busca todas as mensagens pelo id do destinatário as exibe em formato de lista somente com o título e ordenadas por data.
    * Ao selecionar uma mensagem, é exibido uma tela com a visão completa.
    */
   private static void telaCaixaDeEntrada() {
      int escolha = -1;

      while (escolha != 0) {
         System.out.println(barra + "\nCaixa de entrada:\n");

         /* buscando as mensagens: */
         ArrayList<MensagemVO> listaMensagens = mensagemController.buscarMensagensPorDestinatario(usuarioLogado.getIdUsuario());
         int qtdeMensagem = listaMensagens.size();
         if (qtdeMensagem > 0) {

            /* listando todas as mensagens: */
            for (int i = 0; i < qtdeMensagem; i++) {
               resumoMensagem(listaMensagens.get(i), i, qtdeMensagem);
            }

            /* escolhendo uma mensagem: */
            System.out.print("\nDigite o número da mensagem ou 0 para sair: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            /* abrindo a mensagem: */
            if (escolha <= qtdeMensagem && escolha > 0) {
               telaMensagem(listaMensagens.get(escolha - 1));
            }

         } else {
            System.out.println("Caixa de entrada vazia!");
            escolha = 0;
         }
      }
   }

   /***
    * Descriptografa e exibe um resumo da mensagem em uma linha
    * @param mensagem mensagem a ser exibida
    * @param i linha atual
    * @param qtdeMensagem quantidade total de mensagens
    */
   private static void resumoMensagem(MensagemVO mensagem, int i, int qtdeMensagem) {
      try {
         String tituloDescriptografado = SegurancaController.decrypt(mensagem.getMensagemTitulo(), usuarioLogado.getChavePrivada());
         String remetente = mensagem.getNomeRemetente();

         System.out.println(
                 String.format("%" + String.valueOf(qtdeMensagem).length() + "d", (i + 1)) + ".    "
                         + String.format("%-20s    %-12s    %s",
                         tituloDescriptografado.length() > 20 ? tituloDescriptografado.substring(0, 17) + "..." : tituloDescriptografado,
                         remetente.length() > 12 ? remetente.substring(0, 9) + "..." : remetente,
                         mensagem.getMensagemData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
      } catch (Exception e) {
         System.out.println("Erro ao abrir mensagem: " + e.getMessage());
      }
   }

   /***
    * Descriptografa e exibe a mensagem completa
    * @param mensagemVO mensagem a ser exibida
    */
   private static void telaMensagem(MensagemVO mensagemVO) {
      try {
         System.out.println(barra + "\n\n\n");
         System.out.println(negrito + "Data: " + reset + mensagemVO.getMensagemData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
         System.out.println(negrito + "Remetente: " + reset + mensagemVO.getNomeRemetente());
         System.out.println(negrito + "Destinatario: " + reset + mensagemVO.getNomeDestinatario());
         System.out.println(negrito + "Assunto: " + reset + SegurancaController.decrypt(mensagemVO.getMensagemTitulo(), usuarioLogado.getChavePrivada()));
         System.out.println("\"" + SegurancaController.decrypt(mensagemVO.getMensagemCorpo(), usuarioLogado.getChavePrivada()) + "\"");

         System.out.print("\nPressione qualquer tecla para voltar\n\n\n");
         scanner.nextLine();
      } catch (Exception e) {
         System.out.println("Erro ao abrir mensagem: " + e.getMessage());
      }
   }

   /***
    * Exibe a tela de login e cadastro e encaminha para o controller fazer o que tem que fazer.
    * @return UsuarioVO com id preenchido se encontrado ou zerado se não encontrado
    */
   private static UsuarioVO telaLoginCadastro() {
      int escolha = 0;
      final int LOGIN = 1;
      final int CADASTRO = 2;
      UsuarioVO usuario = new UsuarioVO();

      System.out.println("1. Login");
      System.out.println("2. Cadastro");
      System.out.print("Selecione uma opção: ");
      escolha = scanner.nextInt();
      scanner.nextLine(); // Limpar o buffer de entrada

      switch (escolha) {
         case LOGIN:
            System.out.println(barra);
            System.out.print("Digite o nome do usuário: ");
            usuario.setNomeUsuario(scanner.nextLine());
            System.out.print("Digite a senha: ");
            usuario.setSenha(scanner.nextLine());
            usuario = usuarioController.efetuarLogin(usuario);
            if (usuario.getIdUsuario() == 0) {
               System.out.println("Erro: Usuário ou senha incorretos!");
            } else {
               System.out.println("Login efetuado com sucesso!");
            }
            break;
         case CADASTRO:
            System.out.println(barra);
            System.out.print("Digite o nome do usuário: ");
            usuario.setNomeUsuario(scanner.nextLine());
            System.out.print("Digite a senha: ");
            usuario.setSenha(scanner.nextLine());
            usuario = usuarioController.cadastrarUsuario(usuario);
            if (usuario.getIdUsuario() == 0) {
               System.out.println("Erro: Usuário já existe!");
            } else {
               System.out.println("Cadastro efetuado com sucesso!");
            }
            break;
         default:
            System.out.println("Opção inválida!\n");
      }
      return usuario;
   }

   /**
    * printa o logotipo mto top do app
    */
   private static void exibirLogotipo() {
      System.out.println("\r\n" + "                                                 _       \r\n" + "                                                (_)      \r\n" + "  _ __ ___   ___ _ __  ___  __ _  __ _  ___ _ __ _  __ _ \r\n" + " | '_ ` _ \\ / _ \\ '_ \\/ __|/ _` |/ _` |/ _ \\ '__| |/ _` |\r\n" + " | | | | | |  __/ | | \\__ \\ (_| | (_| |  __/ |  | | (_| |\r\n" + " |_| |_| |_|\\___|_| |_|___/\\__,_|\\__, |\\___|_|  |_|\\__,_|\r\n" + "                                  __/ |                  \r\n" + "                                 |___/                   \r");
   }
}
