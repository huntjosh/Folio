package User.UserComponents;

public class Address {
    private int streetNum;
    private String street;
    private String suburb;
    private String postCode;
    private String country;

    public static Address emptyAddress(){ return new Address(); }

    private Address(){

    }

    @Override
    public String toString() {
        return streetNum + " " +
                street + ", " +
                suburb +
                (country != null ? ", " + country :"") +
                (postCode != null ? ", " + postCode : "");
    }

    public Address(int streetNum, String street, String suburb){
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }
}
