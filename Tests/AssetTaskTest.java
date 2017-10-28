package Tests;

import Portfolio.Asset.Asset;
import Portfolio.Task.Task;
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
        Assertions.assertTrue(cleanCar.hasAssets());
    }

    @Test
    void assetWithContrustorTask() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.getTasks().contains(cleanCar));
        Assertions.assertTrue(car.hasTasks());
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

    @Test
    void createAssetWithMultipleTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Task sellCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Sell car").build();
        Task fixCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Fix car").build();

        HashSet<Task> tasks = new HashSet<Task>();
        tasks.add(sellCar);
        tasks.add(fixCar);

        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).tasks(tasks).build();

        Assertions.assertTrue(car.getTasks().contains(sellCar));
        Assertions.assertTrue(car.getTasks().contains(fixCar));
        Assertions.assertTrue(car.getTasks().contains(cleanCar));
        Assertions.assertEquals(3, car.getTasks().size());
    }

    @Test
    void createTaskWithMultipleAssets() {
        Asset car = new Asset.Builder("Mercedes S-class", 30000).build();
        Asset car2 = new Asset.Builder("Mercedes D-class", 10000).build();
        Asset car3 = new Asset.Builder("Mercedes F-class", 1000).build();

        HashSet<Asset> assets = new HashSet<Asset>();
        assets.add(car);
        assets.add(car2);

        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").asset(car3).assets(assets).build();

        Assertions.assertTrue(cleanCar.getAssets().contains(car));
        Assertions.assertTrue(cleanCar.getAssets().contains(car2));
        Assertions.assertTrue(cleanCar.getAssets().contains(car3));
        Assertions.assertEquals(3, cleanCar.getAssets().size());
    }

    @Test
    void addMultipleAssetsToExistingTaskAssets() {
        Asset car = new Asset.Builder("Mercedes S-class", 30000).build();
        Asset car2 = new Asset.Builder("Mercedes D-class", 10000).build();
        Asset car3 = new Asset.Builder("Mercedes F-class", 1000).build();

        HashSet<Asset> assets = new HashSet<>();
        assets.add(car);
        assets.add(car2);

        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();

        Assertions.assertEquals(0, cleanCar.addAssets(assets).size());
        Assertions.assertTrue(cleanCar.linkToAsset(car3));
        Assertions.assertTrue(cleanCar.getAssets().contains(car));
        Assertions.assertTrue(cleanCar.getAssets().contains(car2));
        Assertions.assertTrue(car3.getTasks().contains(cleanCar));
        Assertions.assertTrue(cleanCar.getAssets().contains(car3));
    }
}