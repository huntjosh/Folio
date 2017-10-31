package portfolio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Leaf {

    private HashSet<Leaf> children;
    private Leaf parent;
    private LeafType type;

    public Leaf(HashSet<Leaf> children, Leaf parent, LeafType type) {
        this.type = type;
        this.children = children;
        this.parent = parent;
    }

    public boolean addChild(Leaf child) {
        return children.add(child);
    }

    public boolean removeChild(Leaf child) {
        return children.remove(child);
    }

    public int numberOfChildren() {
        return children.size();
    }

    public Set<Leaf> getChildren() {
        return new HashSet<>(children);
    }

    private void addParent(Leaf parent) {
        this.parent = parent;
    }

    public Leaf getParent() {
        return parent;
    }

    public boolean linkToChild(Leaf child) {
        if (addChild(child)) {
            child.addParent(this);
            return true;
        }
        return false;
    }

    private LeafType getType() {
        return type;
    }

    protected boolean hasChildOfType(LeafType type) {
        for (Leaf child : children) {
            if (child.getType() == type) return true;
        }

        return false;
    }

    public List<Leaf> addChildren(Set<Leaf> newChildren) {
        // Could potentially have duplicate tasks, so need an array list
        List<Leaf> failedChildren = new ArrayList<>();

        for (Leaf child : newChildren) {
            if (!children.add(child)) failedChildren.add(child);
        }

        return failedChildren;
    }

    protected enum LeafType {
        TASK, ASSET
    }
}
