package User;

import Exceptions.UserException;
import User.UserComponents.Contact;

public class UserFactory {
    private UserFactory(){
        // We don't want to allow instantiation
        throw new IllegalStateException();
    }

    public static User newUser(String type, Contact contact) throws UserException {
        if (type.equals("Administrator")) return new Administrator(contact);
        else if (type.equals("Stakeholder")) return new Stakeholder(contact);

        throw new UserException("Unable to create a user of type: " + type);
    }
}
