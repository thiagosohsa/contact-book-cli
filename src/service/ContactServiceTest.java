package service;

import model.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactServiceTest {
    @Test
    void shouldAddContactWhenEmailIsValidAndUnique() {
        ContactService service = new ContactService();
        service.clear();

        AddContactResult result = service.add(
                new Contact("Ana", "11912345678", "ana@gmail.com")
        );

        assertEquals(AddContactResult.SUCCESS, result);
    }
}
