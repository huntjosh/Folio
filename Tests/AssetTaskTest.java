package Tests;

import Portfolio.Asset.Asset;
import Task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.HashSet;

class AssetTaskTest {
    @Test
    void taskWithConstructorAsset() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(cleanCar.getAssets().contains(car));
    }

    @Test
    void assetWithContrustorTask() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.getTasks().contains(cleanCar));
    }

    @Test
    void addTaskToExistingAssetTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Task sellCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Sell car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.linkToTask(sellCar));
        Assertions.assertTrue(car.getTasks().contains(sellCar));
        Assertions.assertEquals(2, car.getTasks().size());
    }

    @Test
    void addDuplicateTaskToExistingAssetTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).task(cleanCar).build();
        });
    }

    @Test
    void addMultipleTasksToExistingAssetTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Task sellCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Sell car").build();
        Task fixCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Fix car").build();

        HashSet<Task> tasks = new HashSet<Task>();
        tasks.add(sellCar);
        tasks.add(fixCar);

        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();

        Assertions.assertEquals(0, car.addTasks(tasks).size());
        Assertions.assertTrue(car.getTasks().contains(sellCar));
        Assertions.assertTrue(car.getTasks().contains(fixCar));
        Assertions.assertTrue(car.getTasks().contains(cleanCar));
        Assertions.assertEquals(3, car.getTasks().size());
    }
}