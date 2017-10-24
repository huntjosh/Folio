package Tests;

import User.UserComponents.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {
    @Test
    void address() {
        Address address = new Address(21, "reeves rd", "opawa");
        Assertions.assertEquals(address.getSuburb(), "opawa");
        Assertions.assertEquals(address.getStreet(), "reeves rd");
        Assertions.assertEquals(address.getStreetNum(), 21);
    }

    @Test
    void optionalAddressFields() {
        Address address = new Address(21, "reeves rd", "opawa");
        address.setPostCode("8052");
        address.setCountry("New Zealand");
        Assertions.assertEquals(address.getPostCode(), "8052");
        Assertions.assertEquals(address.getCountry(), "New Zealand");
    }

    @Test
    void modifyAddressFields() {
        Address address = new Address(21, "reeves rd", "opawa");
        address.setStreetNum(1);
        address.setStreet("Snowdon rd");
        address.setSuburb("Fendalton");
        Assertions.assertEquals(address.getStreetNum(), 1);
        Assertions.assertNotEquals(address.getStreetNum(), 21);
        Assertions.assertEquals(address.getStreet(), "Snowdon rd");
        Assertions.assertNotEquals(address.getStreet(), "reeves rd");
        Assertions.assertEquals(address.getSuburb(), "Fendalton");
        Assertions.assertNotEquals(address.getSuburb(), "opawa");
    }
}
