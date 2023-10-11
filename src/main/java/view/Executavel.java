package view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.MensagemController;
import controller.UsuarioController;
import model.vo.MensagemVO;
import model.vo.UsuarioVO;

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

		while (usuarioLogado.getIdUsuario() == 0) {
			usuarioLogado = telaLoginCadastro();
		}

		while (!sair) {
			exibirMenuPrincipal();
			int escolha = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer de entrada

			switch (escolha) {
			case 1:
				telaCaixaDeEntrada();
				break;
			case 2:
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
	
	/**
	 * Exibe as opções do menu principal
	 */
	private static void exibirMenuPrincipal() {
		System.out.println(barra);
		System.out.println("1. Caixa de entrada");
		System.out.println("2. Enviar mensagem");
		System.out.println("9. Sair");
		System.out.print("Selecione uma opção:");
	}
	
	/**
	 * Busca todas as mensagens pelo id do destinatário as exibe em formato de lista somente com o título e ordenadas por data.
	 * Ao selecionar uma mensagem, é exibido uma tela com a visão completa.
	 */
	private static void telaCaixaDeEntrada() {
		int escolha = -1;
		
		while (escolha != 0) {
			System.out.println(barra + "\nCaixa de entrada:\n");
			
			// buscando as mensagens:
			ArrayList<MensagemVO> listaMensagens = mensagemController.buscarMensagensPorDestinatario(usuarioLogado.getIdUsuario());
			int qtdeMensagem = listaMensagens.size();
			if (qtdeMensagem > 0) {
				// listando todas as mensagens:
				for (int i = 0; i < qtdeMensagem; i++) {
					System.out.println(String.format("%" + String.valueOf(qtdeMensagem).length() + "d", (i + 1)) + ".    "
							+ listaMensagens.get(i));
				}
				System.out.print("\nDigite o número da mensagem ou 0 para sair: ");
				escolha = scanner.nextInt();
				scanner.nextLine(); 
				
				// abrindo a mensagem:
				if (escolha <= qtdeMensagem && escolha > 0) {
					telaMensagem(listaMensagens.get(escolha - 1));
				} 
			} else {
				System.out.println("Caixa de entrada vazia!");
				escolha = 0;
			}
		}
	}

	/**
	 * Abre uma tela com todas as informações da mensagem
	 * @param mensagemVO
	 */
	private static void telaMensagem(MensagemVO mensagemVO) {
		System.out.println(barra+"\n\n\n");
		System.out.println(negrito + "Data: " + reset + mensagemVO.getMensagemData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		System.out.println(negrito + "Remetente: " + reset +  mensagemVO.getNomeRemetente());
		System.out.println(negrito + "Destinatario: " + reset +  mensagemVO.getNomeDestinatario());
		System.out.println(negrito + "Assunto: " + reset +  mensagemVO.getMensagemTitulo());
		System.out.println("\"" + mensagemVO.getMensagemCorpo() + "\"");

		System.out.print("\nPressione qualquer tecla para voltar\n\n\n");
		scanner.nextLine();
	}

	/***
	 * Exibe as opções de login e cadastro e encaminha para o controller fazer o que
	 * tem que fazer.
	 * 
	 * @return Objeto de usuário com ID preenchido se tiver ocorrido tudo bem ou
	 *         zerado em caso de erro.
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
		System.out.println("\r\n" + "                                                 _       \r\n"
				+ "                                                (_)      \r\n"
				+ "  _ __ ___   ___ _ __  ___  __ _  __ _  ___ _ __ _  __ _ \r\n"
				+ " | '_ ` _ \\ / _ \\ '_ \\/ __|/ _` |/ _` |/ _ \\ '__| |/ _` |\r\n"
				+ " | | | | | |  __/ | | \\__ \\ (_| | (_| |  __/ |  | | (_| |\r\n"
				+ " |_| |_| |_|\\___|_| |_|___/\\__,_|\\__, |\\___|_|  |_|\\__,_|\r\n"
				+ "                                  __/ |                  \r\n"
				+ "                                 |___/                   \r");
	}
}
