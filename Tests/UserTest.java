package Tests;

import Portfolio.Asset.Asset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void assetValue() {
        Asset asset = new Asset.Builder("Car", 10000.00).build();
        Assertions.assertEquals(asset.getValue(), 10000.00);
    }
}