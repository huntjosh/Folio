package Tests;
import Task.Task;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;

class TaskTest {
    @org.junit.jupiter.api.Test
    void getDue() {
    }

    @org.junit.jupiter.api.Test
    void createTask(){
        Task task = new Task(LocalDateTime.now(), LocalDateTime.now(), "Inspection");
        Assertions.assertTrue(task.getClass() == Task.class);
    }

    @org.junit.jupiter.api.Test
    void taskName(){
        Task task = new Task(LocalDateTime.now(), LocalDateTime.now(), "Inspection");
        Assertions.assertTrue(task.getName().equals("Inspection"));
    }

    @org.junit.jupiter.api.Test
    void taskNoAssignee(){
        Task task = new Task(LocalDateTime.now(), LocalDateTime.now(), "Inspection");
        Assertions.assertTrue(!task.hasAssignee());
        Assertions.assertTrue(task.getAssignee() == null);
    }

    @org.junit.jupiter.api.Test
    void taskDueDateOnCreation(){
        LocalDateTime timeNow = LocalDateTime.now();
        Task task = new Task(timeNow, LocalDateTime.now(), "Inspection");
        Assertions.assertTrue(task.getDue().isEqual(timeNow));
        Assertions.assertTrue(!task.getDue().isEqual(timeNow.plusSeconds(1)));
    }

    @org.junit.jupiter.api.Test
    void taskCreationDateOnCreation(){
        LocalDateTime timeNow = LocalDateTime.now();
        Task task = new Task(LocalDateTime.now(), timeNow, "Inspection");
        Assertions.assertTrue(task.getCreatedAt().isEqual(timeNow));
        Assertions.assertTrue(!task.getCreatedAt().isEqual(timeNow.plusSeconds(1)));
    }

    @org.junit.jupiter.api.Test
    void taskNameChange(){
        Task task = new Task(LocalDateTime.now(), LocalDateTime.now(), "Inspection");
        task.setName("Maintenance");
        Assertions.assertTrue(task.getName().equals("Maintenance"));
    }
}