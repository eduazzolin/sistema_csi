package view;

import java.util.Scanner;

public class Executavel {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean sair = false;

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

	private static void exibirMenu() {
		System.out.println("Selecione uma opção:");
		System.out.println("1. Criar Cliente");
		System.out.println("2. Listar Clientes");
		System.out.println("3. Enviar Mensagem");
		System.out.println("4. Listar Mensagens");
		System.out.println("5. Sair");
	}
}
