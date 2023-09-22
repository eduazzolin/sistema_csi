package view;

import java.util.Scanner;
import controller.UsuarioController;
import model.vo.UsuarioVO;

public class Executavel {
	private static Scanner scanner = new Scanner(System.in);
	private static UsuarioController usuarioController = new UsuarioController();
	private static UsuarioVO usuarioLogado = new UsuarioVO();

	public static void main(String[] args) {
		boolean sair = false;

		while (usuarioLogado.getIdUsuario() == 0) {
			exibirLogotipo();
			usuarioLogado = telaLoginCadastro();
		}

		while (!sair) {
			exibirMenu();
			int escolha = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer de entrada

			switch (escolha) {
			case 1:
				// Implementar a funcionalidade de criar um cliente (CRUD)
				break;
			case 2:
				// Implementar a funcionalidade de listar clientes (RUD)
				break;
			case 3:
				// Implementar a funcionalidade de enviar mensagem
				break;
			case 4:
				// Implementar a funcionalidade de listar mensagens
				break;
			case 5:
				sair = true;
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		}

		System.out.println("O programa foi encerrado.");
	}

	private static void exibirLogotipo() {
		// código ansii com um logo top
	}

	/***
	 * Exibe as opções de login e cadastro e encaminha 
	 * para o controller fazer o que tem que fazer. 
	 * @return Objeto de usuário com ID preenchido se 
	 * tiver ocorrido tudo bem ou zerado em caso de erro.
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
			System.out.println("-------------\nLogin: ");
			System.out.print("Digite o nome do usuário: ");
			usuario.setNomeUsuario(scanner.nextLine());
			System.out.print("Digite a senha: ");
			usuario.setSenha(scanner.nextLine());
//			usuario = usuarioController.efetuarLogin(usuario);
			if (usuario.getIdUsuario() == 0) {
				System.out.println("Erro: Usuário ou senha incorretos!\n");
			} else {
				System.out.println("Login efetuado com sucesso!\n");
			}
			break;
		case CADASTRO:
			System.out.println("-------------\nCadastro: ");
			System.out.print("Digite o nome do usuário: ");
			usuario.setNomeUsuario(scanner.nextLine());
			System.out.print("Digite a senha: ");
			usuario.setSenha(scanner.nextLine());
//			usuario = usuarioController.cadastrarUsuario(usuario);
			if (usuario.getIdUsuario() == 0) {
				System.out.println("Erro: Usuário já existe!\n");
			} else {
				System.out.println("Cadastro efetuado com sucesso!\n");
			}
			break;
		default:
			System.out.println("Opção inválida!\n");
		}
		return usuario;
	}

	private static void exibirMenu() {
		System.out.println("Selecione uma opção:");
		System.out.println("1. Criar Cliente");
		System.out.println("2. Listar Clientes");
		System.out.println("3. Enviar Mensagem");
		System.out.println("4. Listar Mensagens");
		System.out.println("5. Sair");
	}
}
