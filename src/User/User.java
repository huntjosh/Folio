package User;

import Portfolio.Asset.Asset;
import Portfolio.Portfolio;
import Task.Task;
import User.UserComponents.Contact;
import java.util.ArrayList;
import java.util.HashSet;


public abstract class User {
    HashSet<Task> tasks;
    HashSet<Portfolio> portfolios;
    HashSet<Asset> assets;
    Contact contact;

    public boolean assignTask(Task task) {
        if (!task.assign(this)) return false;

        tasks.add(task);
        return true;
    }

    public boolean removeTask(Task task) {
        // If the task was assigned but not added to the users task list of vice versa, do a safety clear
        if (tasks.contains(task) && (task.getAssignee() != this)) return false;

        task.unassign();
        tasks.remove(task);
        return true;
    }

    public ArrayList<Task> removeAllTasks(){
        // Could potentially have duplicate tasks, so need an array list
        ArrayList<Task> failedTasks = new ArrayList<>();

        for (Task task : tasks){ if (!removeTask(task)) failedTasks.add(task); }

        return failedTasks;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
