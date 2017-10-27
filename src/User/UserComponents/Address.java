package User.UserComponents;

public final class Address {
    private final int streetNum;
    private final String street;
    private final String suburb;
    private final String postCode;
    private final String country;

    @Override
    public String toString() {
        return  streetNum + " " + street + ", " +
                suburb +
                (postCode != null ? ", " + postCode : "") +
                (country != null ? ", " + country :"");
    }

    public Address(int streetNum, String street, String suburb){
        this(streetNum,street, suburb, null);
    }

    public Address(int streetNum, String street, String suburb, String postCode){
        this(streetNum,street, suburb, postCode, null);
    }

    public Address(int streetNum, String street, String suburb, String postCode, String country){
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.postCode = postCode;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNum() {
        return streetNum;
    }
}
