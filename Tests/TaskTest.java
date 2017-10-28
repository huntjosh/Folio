package Tests;

import Portfolio.Task.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import User.Stakeholder;
import User.UserComponents.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void createTask(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(task.getClass() == Task.class, "Portfolio.Task class type is incorrect");
    }

    @Test
    void taskName(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(task.getName().equals("Inspection"), "Portfolio.Task name not set correctly");
    }

    @Test
    void taskNoAssignee(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(!task.hasAssignee(), "Portfolio.Task shouldn't have an assignee at this stage");
        Assertions.assertTrue(task.getAssignee() == null, "Portfolio.Task returned an unexpected assignee");
    }

    @Test
    void taskDueDateOnCreation(){
        LocalDateTime timeNow = LocalDateTime.now().minusSeconds(10);
        Task task = new Task.Builder(LocalDateTime.now(), timeNow, "Inspection").build();
        Assertions.assertTrue(task.getDue().isEqual(timeNow), "Due date not set correctly");
        Assertions.assertTrue(!task.getDue().isEqual(timeNow.plusSeconds(1)), "Due date is equal to different times apparently");
    }

    @Test
    void taskCreationDateOnCreation(){
        LocalDateTime timeNow = LocalDateTime.now().minusSeconds(10);
        Task task = new Task.Builder(timeNow, LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(task.getCreatedAt().isEqual(timeNow), "Portfolio.Task created at didn't set correctly");
        Assertions.assertTrue(!task.getCreatedAt().isEqual(timeNow.plusSeconds(1)), "Portfolio.Task createdat is equal to a different time");
    }

    @Test
    void taskNameChange(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        task.setName("Maintenance");
        Assertions.assertTrue(task.getName().equals("Maintenance"), "Portfolio.Task name didn't change correctly");
    }

    @Test
    void taskChangeDueDate(){
        LocalDateTime time = LocalDateTime.now();
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        task.setDue(time);
        Assertions.assertTrue(task.getDue().isEqual(time), "Portfolio.Task due date change didn't work as expected");
    }

    @Test
    void taskIncorrectNameChange(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        task.setName("Maintenance");
        Assertions.assertFalse(task.getName().equals("Inspection"), "Portfolio.Task name change didn't work as expected");
    }

    @Test
    void taskChangeDueDateIncorrect(){
        LocalDateTime newDueDate = LocalDateTime.now().minusSeconds(5);
        LocalDateTime creationDueDate = LocalDateTime.now();
        Task task = new Task.Builder(LocalDateTime.now(), creationDueDate, "Inspection").build();
        task.setDue(newDueDate);
        Assertions.assertFalse(task.getDue().isEqual(creationDueDate));
    }

    @Test
    void toStringTest() {
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime dueDate = LocalDateTime.now().minusSeconds(10);
        Task task = new Task.Builder(createdDate, dueDate, "Inspection").build();
        Assertions.assertEquals("Name: Inspection, Portfolio.Task Due: " +
                        dueDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)) +
                        ", Portfolio.Task Created At: " +
                        createdDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)) +
                        ", Assignee: None"
                , task.toString());
    }

    @Test
    void taskWithUserOnBuilder(){
        Stakeholder bob = new Stakeholder(
                new Contact.Builder("Jim",
                        "Jones",
                        "02040490234",
                        "Joshhhunt@gmail.com").build());
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").user(bob).build();
        Assertions.assertEquals(bob, task.getAssignee());
    }
}