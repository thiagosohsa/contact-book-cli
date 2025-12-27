package service;

import model.Contact;
import storage.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private final List<Contact> contacts;
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
        this.contacts = repository.load();
    }

    void clear() {
        contacts.clear();
    }

    private String normalizePhone(String phone) {
        return phone.replaceAll("\\D", "");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public AddContactResult add(Contact contact) {
        if (!isValidEmail(contact.getEmail())) {
            return AddContactResult.EMAIL_INVALID;
        }

        for (Contact c : contacts) {
            if (c.getEmail().equalsIgnoreCase(contact.getEmail())) {
                return AddContactResult.EMAIL_DUPLICATE;
            }
        }

        String normalizedPhone = normalizePhone(contact.getPhone());
        Contact normalizedContact = new Contact(contact.getName(), normalizedPhone, contact.getEmail());

        contacts.add(normalizedContact);
        repository.save(contacts);
        return AddContactResult.SUCCESS;
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

    public Contact getByIndex(int index) {
        if (index < 0 || index >= contacts.size()) {
            return null;
        }
        return contacts.get(index);
    }

    public Contact removeByIndex(int index) {
        if (index < 0 || index >= contacts.size()) {
            return null;
        }

        Contact removed = contacts.remove(index);
        repository.save(contacts);
        return removed;
    }
}
