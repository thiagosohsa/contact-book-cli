package service;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public boolean addIfEmailUnique(Contact contact) {
        for (Contact c : contacts) {
            if (c.getEmail().equalsIgnoreCase(contact.getEmail())) {
                return false;
            }
        }
        contacts.add(contact);
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
        return contacts.remove(index);
    }
}
