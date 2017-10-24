package Tests;

import User.UserComponents.Address;
import User.UserComponents.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactTest {
    @Test
    void baseContact() {
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com").build();
        Assertions.assertEquals(contact.getFirstName(), "Josh");
        Assertions.assertEquals(contact.getLastName(), "Hunt");
        Assertions.assertEquals(contact.getPhoneNumber(), "02040490234");
        Assertions.assertEquals(contact.getEmail(), "joshhhunt@gmail.com");
    }

    @Test
    void contactWithAddress() {
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com")
                .address(Address.emptyAddress())
                .build();
        Assertions.assertEquals(contact.getAddress().getClass(), Address.emptyAddress().getClass());
    }

    @Test
    void contactWithAltPhoneNumber() {
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com")
                .altPhone("Home", "033848313")
                .build();
        Assertions.assertEquals(contact.getAltPhoneNumbers().get("Home"), "033848313");
    }

    @Test
    void contactWithAltPhoneNumbers() {
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com")
                .altPhone("Home", "033848313")
                .altPhone("Work", "0212864636")
                .altPhone("Personal", "02040490234")
                .build();
        Assertions.assertEquals(contact.getAltPhoneNumbers().size(), 3);
    }

    @Test
    void removeAltNumberFromContact() {
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com")
                .altPhone("Home", "033848313")
                .altPhone("Work", "0212864636")
                .altPhone("Personal", "02040490234")
                .build();
        Assertions.assertEquals(contact.getAltPhoneNumbers().size(), 3);
        Assertions.assertTrue(contact.removeAltPhoneNumber("Home"));
        Assertions.assertEquals(contact.getAltPhoneNumbers().size(), 2);
    }

    @Test
    void attemptRemoveNonExistingAltPhone() {
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com")
                .altPhone("Home", "033848313")
                .altPhone("Work", "0212864636")
                .altPhone("Personal", "02040490234")
                .build();
        Assertions.assertEquals(contact.getAltPhoneNumbers().size(), 3);
        Assertions.assertFalse(contact.removeAltPhoneNumber("Work2"));
        Assertions.assertEquals(contact.getAltPhoneNumbers().size(), 3);
    }
}
