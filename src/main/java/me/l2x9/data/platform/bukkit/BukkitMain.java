package me.l2x9.data.platform.bukkit;

import lombok.Getter;
import lombok.experimental.Accessors;
import me.l2x9.data.database.ConnectionManager;
import me.l2x9.data.plugin.DataPlugin;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Accessors(fluent = true)
public final class BukkitMain extends JavaPlugin implements DataPlugin {
    private static BukkitMain instance;
    private final BukkitServer server = new BukkitServer(this);
    private ConnectionManager connectionManager;

    public static BukkitMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        connectionManager = new ConnectionManager(server);
        instance = this;
    }

    @Override
    public void onDisable() {
        DataPlugin.super.onDisable();
    }
}
