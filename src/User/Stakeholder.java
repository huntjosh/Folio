package User;

import User.UserComponents.Contact;

public class Stakeholder extends User {
    public Stakeholder(Contact contact){
        super();
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Stakeholder: " + contact.getDisplayName();
    }
}
