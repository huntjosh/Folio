package Task;

import Portfolio.Asset.Asset;
import User.User;
import java.time.LocalDateTime;

public class AssetTask extends Task {
    private Asset asset;

    public AssetTask(LocalDateTime due, LocalDateTime createdAt, String name, Asset asset){
        super(due, createdAt, name);
        this.asset = asset;
    }

    public AssetTask(LocalDateTime due, LocalDateTime createdAt, String name, User user, Asset asset){
        super(due, createdAt, name, user);
        this.asset = asset;
    }
}
