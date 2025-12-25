package service;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public int size() {
        return contacts.size();
    }

    public Contact removeByIndex(int index) {
        if (index < 0 || index >= contacts.size()) {
            return null;
        }
        return contacts.remove(index);
    }
}
