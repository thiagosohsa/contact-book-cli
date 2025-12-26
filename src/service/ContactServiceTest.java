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

        boolean added = service.addIfEmailUnique(
                new Contact("Ana", "11912345678", "ana@gmail.com")
        );

        assertTrue(added);
        assertEquals(1, service.size());
    }
}
