package User.UserComponents;

import java.util.HashMap;

public final class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private String email;
    private HashMap<String, String> altPhoneNumbers;

    @Override
    public String toString() {
        return "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Phone Number: " + phoneNumber +
                ", Email: " + email;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getAltPhoneNumbers() {
        return new HashMap<>(altPhoneNumbers);
    }

    public void setAltPhoneNumbers(HashMap<String, String> altPhoneNumbers) {
        this.altPhoneNumbers = altPhoneNumbers;
    }

    public boolean removeAltPhoneNumber(String key){
        if (altPhoneNumbers.containsKey(key)) {
            altPhoneNumbers.remove(key);
            return true;
        }

        return false;
    }

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private final String phoneNumber;
        private final String email;

        private Address address = null;
        private HashMap<String, String> altPhoneNumbers = new HashMap<>();

        public Builder(String firstName, String lastName, String phoneNumber, String email){
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public Builder address(Address address)
        { this.address = address;     return this; }

        public Builder altPhone(String description, String phoneNumber)
        { this.altPhoneNumbers.put(description, phoneNumber);     return this; }

        public Contact build(){
            return new Contact(this);
        }

    }

    public Contact(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.altPhoneNumbers = builder.altPhoneNumbers;
    }
}
