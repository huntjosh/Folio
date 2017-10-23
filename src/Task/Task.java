package Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import Portfolio.Asset.Asset;
import User.User;

public class Task {
    private LocalDateTime due;
    private LocalDateTime createdAt;
    private String name;
    private User assignee;
    private HashSet<Asset> assets;

    public static class Builder{
        private final String name;
        private final LocalDateTime createdAt;
        private final LocalDateTime due;

        private User user = null;
        private HashSet<Asset> assets = new HashSet<>();

        public Builder(LocalDateTime createdAt, LocalDateTime due, String name){
            this.createdAt = createdAt;
            this.due = due;
            this.name = name;
        }

        public Builder user(User user)
        { this.user = user;     return this; }

        public Builder assets(HashSet<Asset> assets)
        { this.assets = assets;     return this; }

        public Task build(){
            return new Task(this);
        }
    }

    public Task(Builder builder){
        this.due = builder.due;
        this.createdAt = builder.createdAt;
        this.name = builder.name;
        this.assignee = builder.user;
        this.assets = builder.assets;
    }

    public boolean addAsset(Asset asset){ return assets.add(asset); }

    public boolean linkToAsset(Asset asset){
        if (addAsset(asset)) {
            asset.addTask(this);
            return true;
        }
        return false;
    }

    public ArrayList<Asset> addAssets(HashSet<Asset> assets){
        // Could potentially have duplicate tasks, so need an array list
        ArrayList<Asset> failedAsset = new ArrayList<>();

        for (Asset asset : assets){ if (!addAsset(asset)) failedAsset.add(asset); }

        return failedAsset;
    }

    public boolean unassign(){
        if (!hasAssignee()) return false;

        assignee = null;
        return true;
    }

    public boolean assign(User user){
        if (hasAssignee()) return false;

        assignee = user;
        return true;
    }

    public User getAssignee(){ return this.assignee; }

    public boolean hasAssignee(){ return this.assignee != null; }

    public boolean hasAsset(){ return assets.size() > 0; }

    public LocalDateTime getCreatedAt() {
        return LocalDateTime.of(createdAt.toLocalDate(), createdAt.toLocalTime());
    }

    public LocalDateTime getDue(){
        return LocalDateTime.of(due.toLocalDate(), due.toLocalTime());
    }

    public void setDue(LocalDateTime due) { this.due = due; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public ArrayList<Asset> getAssets(){ return new ArrayList<>(assets); }

//    public Task duplicate(){
//        //  We want to return the same task, but with no assignee
//        return new Task(getDue(), getCreatedAt(), getName());
//    }
}
