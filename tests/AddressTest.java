package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import user.usercomponents.Address;

class AddressTest {
    @Test
    void address() {
        Address address = new Address(21, "reeves rd", "opawa");
        Assertions.assertEquals(address.getSuburb(), "opawa");
        Assertions.assertEquals(address.getStreet(), "reeves rd");
        Assertions.assertEquals(address.getStreetNum(), 21);
    }

    @Test
    void addressWithPostCode() {
        Address address = new Address(21, "reeves rd", "opawa", "8052");
        Assertions.assertEquals(address.getPostCode(), "8052");
    }

    @Test
    void addressWithCountry() {
        Address address = new Address(21, "reeves rd", "opawa", "8052", "New Zealand");
        Assertions.assertEquals(address.getCountry(), "New Zealand");
    }

    @Test
    void toStringTestBasic() {
        Address address = new Address(21, "reeves rd", "opawa");
        Assertions.assertEquals("21 reeves rd, opawa", address.toString());
    }

    @Test
    void toStringTestNoCountry() {
        Address address = new Address(21, "reeves rd", "opawa", "8052");
        Assertions.assertEquals("21 reeves rd, opawa, 8052", address.toString());
    }

    @Test
    void toStringTestNoPostCode() {
        Address address = new Address(21, "reeves rd", "opawa", "8052", "New Zealand");
        Assertions.assertEquals("21 reeves rd, opawa, 8052, New Zealand", address.toString());
    }
}
