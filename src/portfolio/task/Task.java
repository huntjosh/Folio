package portfolio.task;

import portfolio.Leaf;
import portfolio.asset.Asset;
import user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Set;

public class Task extends Leaf {
    private final LocalDateTime createdAt;
    private LocalDateTime due;
    private String name;
    private User assignee;

    Task(Builder builder) {
        super(builder.children, builder.parent, LeafType.TASK);
        this.due = builder.due;
        this.createdAt = builder.createdAt;
        this.name = builder.name;
        this.assignee = builder.user;

        // Now that the asset is being created, we can add the link from the task to this asset
        for (Leaf leaf : builder.children) {
            linkToChild(leaf);
        }
    }

    public void unassign() {
        assignee = null;
    }

    public boolean assign(User user) {
        if (hasAssignee()) return false;

        assignee = user;
        return true;
    }

    public User getAssignee() {
        return this.assignee;
    }

    public boolean hasAssignee() {
        return this.assignee != null;
    }

    public LocalDateTime getCreatedAt() {
        return LocalDateTime.of(createdAt.toLocalDate(), createdAt.toLocalTime());
    }

    public LocalDateTime getDue() {
        return LocalDateTime.of(due.toLocalDate(), due.toLocalTime());
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

    public boolean hasAsset() {
        return hasChildOfType(LeafType.ASSET);
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", task Due: " + due.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)) +
                ", task Created At: " + createdAt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)) +
                ", Assignee: " + (hasAssignee() ? assignee.getContact().getDisplayName() : "None");
    }

    public static class Builder {
        private final String name;
        private final LocalDateTime createdAt;
        private final LocalDateTime due;

        private User user = null;
        private Leaf parent = null;
        private HashSet<Leaf> children = new HashSet<>();


        public Builder(LocalDateTime createdAt, LocalDateTime due, String name) {
            this.createdAt = createdAt;
            this.due = due;
            this.name = name;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder asset(Asset asset) {
            if (!children.add(asset)) throw new IllegalStateException("Unable to add asset: " + asset.getName());

            return this;
        }

        public Builder assets(Set<Asset> assets) {
            for (Asset asset : assets) asset(asset);
            return this;
        }

        public Builder parent(Leaf parent) {
            this.parent = parent;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
