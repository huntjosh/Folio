package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import portfolio.Leaf;
import portfolio.asset.Asset;
import portfolio.task.Task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class AssetTaskTest {
    @Test
    void taskWithConstructorAsset() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.getChildren().contains(cleanCar));
        Assertions.assertTrue(cleanCar.hasAsset());
    }

    @Test
    void assetWithContrustorTask() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.getChildren().contains(cleanCar));
        Assertions.assertTrue(car.hasTask());
    }

    @Test
    void addTaskToExistingAssetTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Task sellCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Sell car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.addChild(sellCar));
        Assertions.assertTrue(car.getChildren().contains(sellCar));
        Assertions.assertEquals(2, car.numberOfChildren());
    }

    @Test
    void addDuplicateTaskToExistingAssetTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Assertions.assertThrows(IllegalStateException.class, () ->
                new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).task(cleanCar).build()
        );
    }

    @Test
    void addMultipleTasksToExistingAssetTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Task sellCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Sell car").build();
        Task fixCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Fix car").build();

        HashSet<Leaf> tasks = new HashSet<>();
        tasks.add(sellCar);
        tasks.add(fixCar);

        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();

        Assertions.assertEquals(0, car.addChildren(tasks).size());
        Assertions.assertTrue(car.getChildren().contains(sellCar));
        Assertions.assertTrue(car.getChildren().contains(fixCar));
        Assertions.assertTrue(car.getChildren().contains(cleanCar));
        Assertions.assertEquals(3, car.getChildren().size());
    }

    @Test
    void createAssetWithMultipleTasks() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Task sellCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Sell car").build();
        Task fixCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Fix car").build();

        HashSet<Task> tasks = new HashSet<>();
        tasks.add(sellCar);
        tasks.add(fixCar);

        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).tasks(tasks).build();

        Assertions.assertTrue(car.getChildren().contains(sellCar));
        Assertions.assertTrue(car.getChildren().contains(fixCar));
        Assertions.assertTrue(car.getChildren().contains(cleanCar));
        Assertions.assertEquals(3, car.getChildren().size());
    }

    @Test
    void createTaskWithMultipleAssets() {
        Asset car = new Asset.Builder("Mercedes S-class", 30000).build();
        Asset car2 = new Asset.Builder("Mercedes D-class", 10000).build();
        Asset car3 = new Asset.Builder("Mercedes F-class", 1000).build();

        HashSet<Asset> assets = new HashSet<>();
        assets.add(car);
        assets.add(car2);

        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").asset(car3).assets(assets).build();

        Set<Leaf> cleanCarChildren = cleanCar.getChildren();
        Assertions.assertTrue(cleanCarChildren.contains(car));
        Assertions.assertTrue(cleanCarChildren.contains(car2));
        Assertions.assertTrue(cleanCarChildren.contains(car3));
        Assertions.assertEquals(3, cleanCar.numberOfChildren());
    }

    @Test
    void addMultipleAssetsToExistingTaskAssets() {
        Asset car = new Asset.Builder("Mercedes S-class", 30000).build();
        Asset car2 = new Asset.Builder("Mercedes D-class", 10000).build();
        Asset car3 = new Asset.Builder("Mercedes F-class", 1000).build();

        HashSet<Leaf> assets = new HashSet<>();
        assets.add(car);
        assets.add(car2);

        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();

        Assertions.assertEquals(0, cleanCar.addChildren(assets).size());
        Assertions.assertTrue(cleanCar.addChild(car3));

        Set<Leaf> cleanCarChildren = cleanCar.getChildren();
        Assertions.assertTrue(cleanCarChildren.contains(car));
        Assertions.assertTrue(cleanCarChildren.contains(car2));
        Assertions.assertTrue(cleanCarChildren.contains(car3));
    }

    @Test
    void removeTaskFromAsset() {
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        Asset car = new Asset.Builder("Mercedes S-class", 30000).task(cleanCar).build();
        Assertions.assertTrue(car.getChildren().contains(cleanCar));
        Assertions.assertTrue(car.removeChild(cleanCar));
        Assertions.assertFalse(car.hasTask());
        Assertions.assertEquals(0, car.numberOfChildren());
    }

    @Test
    void removeAssetFromTask() {
        Asset car = new Asset.Builder("Mercedes S-class", 30000).build();
        Task cleanCar = new Task.Builder(LocalDateTime.now(), LocalDateTime.now(), "Clean car").build();
        car.addChild(cleanCar);
        Assertions.assertTrue(car.getChildren().contains(cleanCar));
        Assertions.assertTrue(car.removeChild(cleanCar));
        Assertions.assertFalse(car.hasTask());
        Assertions.assertEquals(0, car.numberOfChildren());
    }
}