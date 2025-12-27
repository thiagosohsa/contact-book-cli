package storage;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class InMemoryContactRepository implements ContactRepository {

    private List<Contact> data = new ArrayList<>();

    @Override
    public List<Contact> load() {
        return new ArrayList<>(data);
    }

    @Override
    public void save(List<Contact> contacts) {
        data = new ArrayList<>(contacts);
    }
}
