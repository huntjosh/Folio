package user;

import user.usercomponents.Contact;

public class Administrator extends User {
    Administrator(Contact contact) {
        super(contact);
    }

    @Override
    public String toString() {
        return "Administrator: " + getContact().getDisplayName();
    }
}
