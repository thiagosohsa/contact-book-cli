import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- AGENDA DE CONTATOS ---");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - Remover contato");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Adicionar contato (ainda não implementado)");
                    break;
                case 2:
                    System.out.println("Listar contatos (ainda não implementado)");
                    break;
                case 3:
                    System.out.println("Buscar por nome (ainda não implementado)");
                    break;
                case 4:
                    System.out.println("Remover contato (ainda não implementado)");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (option != 0);

        sc.close();
    }
}