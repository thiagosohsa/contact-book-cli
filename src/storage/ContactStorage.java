package storage;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactStorage implements ContactRepository {
    private static final String FILE_PATH = "contacts.csv";

    @Override
    public List<Contact> load() {
        List<Contact> contacts = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return contacts;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar contatos.");
        }
        return contacts;
    }

    @Override
    public void save(List<Contact> contacts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact c : contacts) {
                bw.write(c.getName() + ";" + c.getPhone() + ";" + c.getEmail());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos.");
        }
    }
}
