package User;

import Exceptions.TaskException;
import Task.Task;

import java.util.ArrayList;

public abstract class User {
    private ArrayList<Task> tasks;

    public void assignTask(Task task) throws TaskException {
        if (task.hasAssignee()) throw new TaskException();

        task.assign(this);
        tasks.add(task);
    }

    public void removeTask(Task task) throws TaskException {
        if (!tasks.contains(task)) throw new TaskException();

        task.unassign();
        tasks.remove(task);
    }

    public int removeAllTasks(){
        int taskRemovedCount = 0;
        for (Task task : this.tasks){
            task.unassign();
            taskRemovedCount++;
        }

        return taskRemovedCount;
    }
}
