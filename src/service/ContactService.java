package service;

import model.Contact;
import storage.ContactStorage;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private List<Contact> contacts = new ArrayList<>();

    private ContactStorage storage = new ContactStorage();

    public ContactService() {
        this.contacts = storage.load();
    }

    public void add(Contact contact) {
        contacts.add(contact);
        storage.save(contacts);
    }

    private String normalizePhone(String phone) {
        return phone.replaceAll("\\D", "");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean addIfEmailUnique(Contact contact) {
        if (!isValidEmail(contact.getEmail())) {
            return false;
        }

        for (Contact c : contacts) {
            if (c.getEmail().equalsIgnoreCase(contact.getEmail())) {
                return false;
            }
        }

        String normalizedPhone = normalizePhone(contact.getPhone());

        Contact normalizedContact = new Contact(contact.getName(), normalizedPhone, contact.getEmail());

        contacts.add(normalizedContact);
        storage.save(contacts);
        return true;
    }

    public List<Contact> getAll() {
        return List.copyOf(contacts);
    }

    public List<Contact> listAll() {
        return new ArrayList<>(contacts);
    }

    public int size() {
        return contacts.size();
    }

    public List<Contact> findByName(String query) {
        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(c);
            }
        }

        return result;
    }

    public Contact removeByIndex(int index) {
        if (index < 0 || index >= contacts.size()) {
            return null;
        }

        Contact removed = contacts.remove(index);
        storage.save(contacts);
        return removed;
    }
}
