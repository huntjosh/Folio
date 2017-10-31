package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import user.usercomponents.Address;
import user.usercomponents.Contact;

class ContactTest {
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
        Address address = new Address(21, "Reeves rd", "Opawa");
        Contact contact = new Contact.Builder("Josh", "Hunt", "02040490234", "joshhhunt@gmail.com")
                .address(address)
                .build();
        Assertions.assertEquals(contact.getAddress().getClass(), address.getClass());
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

    @Test
    void toStringTest() {
        Contact contact = new Contact.Builder(
                "Josh",
                "Hunt",
                "02040490234",
                "joshhhunt@gmail.com")
                .build();
        Assertions.assertEquals("First Name: Josh, Last Name: Hunt, Phone Number: 02040490234, Email: joshhhunt@gmail.com", contact.toString());
    }

    @Test
    void settersTest() {
        Contact contact = new Contact.Builder(
                "Josh",
                "Hunt",
                "02040490234",
                "joshhhunt@gmail.com")
                .build();
        contact.setFirstName("bob");
        contact.setLastName("jones");
        contact.setEmail("bob@jones.com");
        contact.setPhoneNumber("0212864636");
        contact.addAltPhoneNumber("0222938293", "business");

        Assertions.assertEquals("bob", contact.getFirstName());
        Assertions.assertEquals("jones", contact.getLastName());
        Assertions.assertEquals("bob@jones.com", contact.getEmail());
        Assertions.assertEquals("0212864636", contact.getPhoneNumber());
        Assertions.assertEquals("0222938293", contact.getAltPhoneNumbers().get("business"));
    }

    @Test
    void altPhoneNumbers() {
        Contact contact = new Contact.Builder(
                "Josh",
                "Hunt",
                "02040490234",
                "joshhhunt@gmail.com")
                .build();

        contact.addAltPhoneNumber("0222938293", "business");

        Assertions.assertEquals("0222938293", contact.getAltPhoneNumbers().get("business"));
    }
}
