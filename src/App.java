import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Contact> contacts = new ArrayList<>();

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

                    contacts.add(new Contact(name, phone, email));
                    System.out.println("Contato adicionado!");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE CONTATOS ---");

                    if (contacts.isEmpty()) {
                        System.out.println("Nenhum contato cadastrado.");
                    } else {
                        for (int i = 0; i < contacts.size(); i++) {
                            Contact c = contacts.get(i);
                            System.out.printf(
                                    "%d - %s | %s | %s%n",
                                    (i + 1),
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

                    for (Contact c : contacts) {
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