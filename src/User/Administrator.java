package User;

import User.UserComponents.Contact;

public class Administrator extends User {
    public Administrator(Contact contact){
        super();
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Administrator: " + contact.getDisplayName();
    }
}
