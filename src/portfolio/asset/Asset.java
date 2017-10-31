package portfolio.asset;

import portfolio.Leaf;
import portfolio.task.Task;

import java.util.HashSet;
import java.util.Set;

public class Asset extends Leaf {
    private String name;
    private double value;

    Asset(Builder builder) {
        super(builder.children, builder.parent, LeafType.ASSET);
        this.name = builder.name;
        this.value = builder.value;

        // Now that the asset is being created, we can add links to the children
        for (Leaf leaf : builder.children) {
            linkToChild(leaf);
        }
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasTask() {
        return hasChildOfType(LeafType.TASK);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Value: " + value;
    }

    public static class Builder {
        private final String name;
        private final double value;

        private Leaf parent = null;
        private HashSet<Leaf> children = new HashSet<>();

        public Builder(String name, double value) {
            this.value = value;
            this.name = name;
        }

        public Builder task(Task task) {
            if (!children.add(task)) throw new IllegalStateException("Unable to add task: " + task.getName());

            return this;
        }

        public Builder tasks(Set<Task> tasks) {
            for (Task task : tasks) task(task);
            return this;
        }

        public Builder parent(Leaf parent) {
            this.parent = parent;
            return this;
        }

        public Asset build() {
            return new Asset(this);
        }
    }
}
