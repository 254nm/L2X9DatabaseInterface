package org.minestruck.coredata.platform.bukkit;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.plugin.java.JavaPlugin;
import org.minestruck.coredata.database.ConnectionManager;
import org.minestruck.coredata.plugin.DataPlugin;
import org.slf4j.Logger;

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
        connectionManager = new ConnectionManager(server);
        instance = this;
    }

    @Override
    public void onDisable() {
        DataPlugin.super.onDisable();
    }

    @Override
    public Logger logger() {
        return logger();
    }
}
