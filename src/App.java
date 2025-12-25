import model.Contact;
import service.ContactService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ContactService contactService = new ContactService();

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
                    System.out.print("Nome: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Telefone: ");
                    String phone = sc.nextLine().trim();

                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                        System.out.println("Erro: nome, telefone e email são obrigatórios.");
                        break;
                    }

                    boolean added = contactService.addIfEmailUnique(new Contact(name, phone, email));

                    if (added) {
                        System.out.println("Contato adicionado!");
                    } else {
                        System.out.println("Erro: já existe alguém com esse email.");
                    }

                    break;

                case 2:
                    System.out.println("\n--- LISTA DE CONTATOS ---");

                    if (contactService.getAll().isEmpty()) {
                        System.out.println("Nenhum contato cadastrado.");
                    } else {
                        int index = 1;
                        for (Contact c : contactService.getAll()) {
                            System.out.printf(
                                    "%d - %s | %s | %s%n",
                                    index++,
                                    c.getName(),
                                    c.getPhone(),
                                    c.getEmail());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Buscar por nome: ");
                    String search = sc.nextLine().trim().toLowerCase();

                    boolean found = false;
                    System.out.println("\n--- RESULTADO DA BUSCA ---");

                    for (Contact c : contactService.getAll()) {
                        if (c.getName().toLowerCase().contains(search)) {
                            System.out.printf(
                                    "%s | %s | %s%n",
                                    c.getName(),
                                    c.getPhone(),
                                    c.getEmail());
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Nenhum contato encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o número do contato para remover: ");
                    int removeNumber = sc.nextInt();
                    sc.nextLine(); // limpa buffer

                    int removeIndex = removeNumber - 1;

                    Contact removed = contactService.removeByIndex(removeIndex);

                    if (removed == null) {
                        System.out.println("Contato inválido.");
                    } else {
                        System.out.println("Contato removido: " + removed.getName());
                    }
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