package Portfolio.Asset;

import java.util.ArrayList;
import java.util.HashSet;
import Task.Task;

public class Asset {
    private String name;
    private double value;
    private HashSet<Task> tasks;

    public static class Builder{
        private final String name;
        private final double value;
        private HashSet<Task> tasks = new HashSet<Task>();

        public Builder(String name, double value){
            this.value = value;
            this.name = name;
        }

        public Builder task(Task task)
        {
            if (!this.tasks.add(task)) throw new IllegalStateException("Unable to add task: " + task.getName());

            return this;
        }

        public Builder tasks(HashSet<Task> tasks)
        { this.tasks = tasks;     return this; }

        public Asset build(){
            return new Asset(this);
        }
    }

    public Asset(Builder builder){
        this.name = builder.name;
        this.value = builder.value;
        this.tasks = new HashSet<>();

        // Now that the Asset is being created, we can add the link from the Task to this Asset
        for (Task task : builder.tasks){ linkToTask(task); }
    }

    public double getValue(){ return value; }

    public void setValue(double value){ this.value = value; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public HashSet<Task> getTasks(){ return new HashSet<>(tasks); }

    public boolean addTask(Task task){ return tasks.add(task); }

    public boolean linkToTask(Task task){
        if (addTask(task)) {
            task.addAsset(this);
            return true;
        }
        return false;
    }

    public ArrayList<Task> addTasks(HashSet<Task> tasks){
        // Could potentially have duplicate tasks, so need an array list
        ArrayList<Task> failedTasks = new ArrayList<Task>();

        for (Task task : tasks){
            if (!addTask(task)) failedTasks.add(task);
            else task.addAsset(this);
        }

        return failedTasks;
    }
}
