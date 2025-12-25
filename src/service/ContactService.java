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
}
