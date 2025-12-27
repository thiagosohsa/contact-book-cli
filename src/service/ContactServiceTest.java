package service;

import model.Contact;
import org.junit.jupiter.api.Test;
import storage.ContactRepository;
import storage.ContactStorage;
import storage.InMemoryContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactServiceTest {
    @Test
    void shouldAddContactWhenEmailIsValidAndUnique() {
        ContactRepository repository = new InMemoryContactRepository();
        ContactService service = new ContactService(repository);

        AddContactResult result = service.add(
                new Contact("Ana", "11912345678", "ana@gmail.com")
        );

        assertEquals(AddContactResult.SUCCESS, result);
        assertEquals(1, service.size());
    }
}
