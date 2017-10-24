package Core;

import java.util.ArrayList;
import java.util.HashSet;

public class HashSetHelper {
    public static <T> ArrayList<T> addToHashSet(HashSet<T> existingItems, HashSet<T> newItems){
        // Could potentially have duplicate tasks, so need an array list
        ArrayList<T> failedItems = new ArrayList<>();

        for (T item : newItems){ if (!existingItems.add(item)) failedItems.add(item); }

        return failedItems;
    }
}
