package service;

import model.Contact;
import org.junit.jupiter.api.Test;
import storage.ContactStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactServiceTest {
    @Test
    void shouldAddContactWhenEmailIsValidAndUnique() {
        ContactStorage storage = new ContactStorage();
        ContactService service = new ContactService(storage);
        service.clear();

        AddContactResult result = service.add(
                new Contact("Ana", "11912345678", "ana@gmail.com")
        );

        assertEquals(AddContactResult.SUCCESS, result);
    }
}
