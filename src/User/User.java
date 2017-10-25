package User;

import Portfolio.Asset.Asset;
import Portfolio.Portfolio;
import Task.Task;
import User.UserComponents.Contact;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public abstract class User {
    HashSet<Task> tasks;
    HashSet<Portfolio> portfolios;
    HashSet<Asset> assets;
    Contact contact;

    public User(){
        tasks = new HashSet<Task>();
        portfolios = new HashSet<Portfolio>();
        assets = new HashSet<Asset>();
    }

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

    public void removeAllTasks(){
        Iterator<Task> iter = tasks.iterator();
        while (iter.hasNext()) {
            iter.next().unassign();
            iter.remove();
        }
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public HashSet<Asset> getAssets() {
        return new HashSet<>(assets);
    }

    public HashSet<Task> getTasks() {
        return new HashSet<>(tasks);
    }

    public HashSet<Portfolio> getPortfolios() {
        return new HashSet<>(portfolios);
    }

    public boolean addAsset(Asset asset) {
        return assets.add(asset);
    }

    @Override
    public String toString() {
        return "User: " + contact.getDisplayName();
    }
}
