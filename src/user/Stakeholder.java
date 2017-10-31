package user;

import user.usercomponents.Contact;

public class Stakeholder extends User {
    public Stakeholder(Contact contact) {
        super(contact);
    }

    @Override
    public String toString() {
        return "Stakeholder: " + getContact().getDisplayName();
    }
}
