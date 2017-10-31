package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import portfolio.asset.Asset;

class AssetTest {
    @Test
    void assetValue() {
        Asset asset = new Asset.Builder("Car", 10000.00).build();
        Assertions.assertEquals(asset.getValue(), 10000.00);
    }

    @Test
    void assetValueChange() {
        Asset asset = new Asset.Builder("Car", 10000).build();
        asset.setValue(10000.02);
        Assertions.assertEquals(asset.getValue(), 10000.02);
        Assertions.assertNotEquals(asset.getValue(), 10000);
    }

    @Test
    void assetNameChange() {
        Asset asset = new Asset.Builder("Car", 10000).build();
        asset.setName("Bus");
        Assertions.assertEquals(asset.getName(), "Bus");
        Assertions.assertNotEquals(asset.getName(), "Car");
    }

    @Test
    void toStringTest() {
        Asset asset = new Asset.Builder("Car", 10000).build();
        Assertions.assertEquals("Name: Car, Value: 10000.0", asset.toString());
    }
}