package tests;

import exceptions.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import portfolio.asset.Asset;
import portfolio.task.Task;
import user.Stakeholder;
import user.User;
import user.UserFactory;
import user.usercomponents.Contact;

import java.time.LocalDateTime;

class UserTest {
    @Test
    void create() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Assertions.assertTrue(bob.getClass() == Stakeholder.class, "user class type is incorrect");
    }

    @Test
    void getContact() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Assertions.assertEquals("Bobby9999@gmail.com", bob.getContact().getEmail());
    }

    @Test
    void addTask() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Task inspection = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        bob.assignTask(inspection);
        Assertions.assertTrue(bob.getTasks().contains(inspection));
        Assertions.assertEquals(inspection.getAssignee(), bob);
        Assertions.assertEquals(1, bob.getTasks().size());
    }

    @Test
    void addAsset() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Asset lambo = new Asset.Builder("Lambo", 100000).build();
        Assertions.assertTrue(bob.addAsset(lambo));
        Assertions.assertTrue(bob.getAssets().contains(lambo));
        Assertions.assertEquals(1, bob.getAssets().size());
    }

    @Test
    void removeOnlyTask() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Task inspection = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(bob.assignTask(inspection));
        Assertions.assertTrue(bob.getTasks().contains(inspection));
        Assertions.assertTrue(bob.removeTask(inspection));
        Assertions.assertFalse(bob.getTasks().contains(inspection));
    }

    @Test
    void removeOneOfManyTasks() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Task inspection = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Task cleaning = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Cleaning").build();
        Assertions.assertTrue(bob.assignTask(inspection));
        Assertions.assertTrue(bob.assignTask(cleaning));
        Assertions.assertTrue(bob.getTasks().contains(inspection) && bob.getTasks().contains(cleaning));
        bob.removeTask(inspection);
        Assertions.assertFalse(bob.getTasks().contains(inspection));
    }

    @Test
    void removeAllTasks() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Task inspection = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Task cleaning = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Cleaning").build();
        Assertions.assertTrue(bob.assignTask(inspection));
        Assertions.assertTrue(bob.assignTask(cleaning));
        Assertions.assertTrue(bob.getTasks().contains(inspection) && bob.getTasks().contains(cleaning));
        bob.removeAllTasks();
        Assertions.assertEquals(bob.getTasks().size(), 0);
    }

    @Test
    void removeAllOneTasks() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Task inspection = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(bob.assignTask(inspection));
        Assertions.assertTrue(bob.getTasks().contains(inspection));
        bob.removeAllTasks();
        Assertions.assertEquals(bob.getTasks().size(), 0);
    }

    @Test
    void toStringTest() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;
        try {
            bob = UserFactory.newUser("Stakeholder", bobContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }
        Assertions.assertEquals(bob.toString(), "Stakeholder: Bob Jones");
    }

    @Test
    void changeContact() {
        Contact bobContact = new Contact.Builder("Bob", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        Contact jimContact = new Contact.Builder("Jim", "Jones", "02040490234", "Bobby9999@gmail.com").build();
        User bob = null;

        try {
            bob = UserFactory.newUser("Administrator", jimContact);
        } catch (UserException ex) {
            Assertions.fail("user not instantiated");
        }

        bob.setContact(bobContact);
        Assertions.assertNotEquals(jimContact, bob.getContact());
        Assertions.assertEquals(bobContact, bob.getContact());
    }
}