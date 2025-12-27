import model.Contact;
import service.AddContactResult;
import service.ContactService;
import storage.ContactStorage;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ContactStorage storage = new ContactStorage();
        ContactService contactService = new ContactService(storage);

        int option;

        do {
            System.out.println("\n--- AGENDA DE CONTATOS ---");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - Remover contato");
            System.out.println("0 - Sair");

            option = readMenuOption(sc);

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
                    System.out.println(MSG_INVALID_OPTION);
            }
        } while (option != 0);

        sc.close();
    }

    private static Integer readInt(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static final String MSG_INVALID_OPTION = "✖ Opção inválida.";
    private static final String MSG_EMPTY_LIST = "ℹ Nenhum contato cadastrado.";
    private static final String MSG_INVALID_CONTACT = "✖ Contato inválido.";

    private static int readMenuOption(Scanner sc) {
        System.out.print("Escolha uma opção: ");
        String input = sc.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
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

        AddContactResult result = service.add(
                new Contact(name, phone, email)
        );

        switch (result) {
            case SUCCESS:
                System.out.println("✔ Contato adicionado com sucesso!");
                break;
            case EMAIL_INVALID:
                System.out.println("✖ Email inválido.");
                break;
            case EMAIL_DUPLICATE:
                System.out.println("✖ Já existe um contato cadastrado com esse email.");
        }
    }

    private static void listContacts(ContactService contactService) {
        System.out.println("\n--- LISTA DE CONTATOS ---");

        var contacts = contactService.listAll();

        if (contacts.isEmpty()) {
            System.out.println(MSG_EMPTY_LIST);
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
            System.out.println("i Nenhum contato encontrado.");
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

        Contact contact = contactService.getByIndex(removeIndex);

        if (contact == null) {
            System.out.println(MSG_INVALID_CONTACT);
            return;
        }

        System.out.print("Tem certeza que deseja remover " + contact.getName() + "? (s/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (!confirm.equals("s")) {
            System.out.println("i Remoção cancelada.");
            return;
        }

        Contact removed = contactService.removeByIndex(removeIndex);

        if (removed == null) {
            System.out.println(MSG_INVALID_CONTACT);
        } else {
            System.out.println("✔ Contato removido: " + removed.getName());
        }
    }
}