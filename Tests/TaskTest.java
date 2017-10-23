package Tests;

import Exceptions.TaskException;
import Task.Task;
import java.time.LocalDateTime;
import User.Stakeholder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void createTask(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(task.getClass() == Task.class, "Task class type is incorrect");
    }

    @Test
    void taskName(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(task.getName().equals("Inspection"), "Task name not set correctly");
    }

    @Test
    void taskNoAssignee(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        Assertions.assertTrue(!task.hasAssignee(), "Task shouldn't have an assignee at this stage");
        Assertions.assertTrue(task.getAssignee() == null, "Task returned an unexpected assignee");
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
        Assertions.assertTrue(task.getCreatedAt().isEqual(timeNow), "Task created at didn't set correctly");
        Assertions.assertTrue(!task.getCreatedAt().isEqual(timeNow.plusSeconds(1)), "Task createdat is equal to a different time");
    }

    @Test
    void taskNameChange(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        task.setName("Maintenance");
        Assertions.assertTrue(task.getName().equals("Maintenance"), "Task name didn't change correctly");
    }

    @Test
    void taskChangeDueDate(){
        LocalDateTime time = LocalDateTime.now();
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        task.setDue(time);
        Assertions.assertTrue(task.getDue().isEqual(time), "Task due date change didn't work as expected");
    }

    @Test
    void taskIncorrectNameChange(){
        Task task = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Inspection").build();
        task.setName("Maintenance");
        Assertions.assertFalse(task.getName().equals("Inspection"), "Task name change didn't work as expected");
    }

    @Test
    void taskChangeDueDateIncorrect(){
        LocalDateTime newDueDate = LocalDateTime.now().minusSeconds(5);
        LocalDateTime creationDueDate = LocalDateTime.now();
        Task task = new Task.Builder(LocalDateTime.now(), creationDueDate, "Inspection").build();
        task.setDue(newDueDate);
        Assertions.assertFalse(task.getDue().isEqual(creationDueDate));
    }
}