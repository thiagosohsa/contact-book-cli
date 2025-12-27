package storage;

import model.Contact;

import java.util.List;

public interface ContactRepository {

    List<Contact> load();

    void save(List<Contact> contacts);
}
