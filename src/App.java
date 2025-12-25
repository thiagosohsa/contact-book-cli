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
                    addContact(sc, contactService);
                    break;

                case 2:
                    listContacts(contactService);
                    break;

                case 3:
                    searchContacts(sc, contactService);
                    break;

                case 4:
                    removeContact(sc, contactService);
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

    private static void addContact(Scanner sc, ContactService service) {
        System.out.print("Nome: ");
        String name = sc.nextLine().trim();

        System.out.print("Telefone: ");
        String phone = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            System.out.println("Erro: nome, telefone e email são obrigatórios.");
            return;
        }

        boolean added = service.addIfEmailUnique(new Contact(name, phone, email));

        if (added) {
            System.out.println("Contato adicionado!");
        } else {
            System.out.println("Erro: já existe alguém com esse email.");
        }
    }

    private static void listContacts(ContactService contactService) {
        System.out.println("\n--- LISTA DE CONTATOS ---");

        var contacts = contactService.listAll();

        if (contacts.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }

        int index = 1;
        for (Contact c : contacts) {
            System.out.printf(
                    "%d - %s | %s | %s%n",
                    index++,
                    c.getName(),
                    c.getPhone(),
                    c.getEmail());
        }
    }

    private static void searchContacts(Scanner sc, ContactService contactService) {
        System.out.print("Buscar por nome: ");
        String search = sc.nextLine().trim();

        var results = contactService.findByName(search);

        System.out.println("\n--- RESULTADO DA BUSCA ---");

        if (results.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
            return;
        }

        for (Contact c : results) {
            System.out.printf(
                    "%s | %s | %s%n",
                    c.getName(),
                    c.getPhone(),
                    c.getEmail());
        }
    }

    private static void removeContact(Scanner sc, ContactService contactService) {
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
    }
}