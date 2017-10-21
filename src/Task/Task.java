package Task;

import java.time.LocalDateTime;
import User.User;

public class Task {
    private LocalDateTime due;
    private LocalDateTime createdAt;
    private String name;
    private User assignee;

    public Task(LocalDateTime due, LocalDateTime createdAt, String name){
        this.due = due;
        this.createdAt = createdAt;
        this.name = name;
        this.assignee = null;
    }

    public Task(LocalDateTime due, LocalDateTime createdAt, String name, User user){
        this.due = due;
        this.createdAt = createdAt;
        this.name = name;
        this.assignee = user;
    }

    public void unassign(){
        assignee = null;
    }

    public void assign(User user){
        assignee = user;
    }

    public User getAssignee(){
        return this.assignee;
    }

    public boolean hasAssignee(){
        return this.assignee != null;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
