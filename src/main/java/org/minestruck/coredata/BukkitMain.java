package org.minestruck.coredata;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.minestruck.coredata.database.ConnectionManager;
import org.slf4j.Logger;

import java.sql.SQLException;

@Accessors(fluent = true)
public final class BukkitMain extends JavaPlugin implements MCServer {
    @Getter
    private static ConnectionManager connectionManager;

    @Override
    public void onEnable() {
        connectionManager = new ConnectionManager(this);
    }

    @Override
    public void onDisable() {
        try {
            connectionManager.mysql().connection().close();
        } catch (SQLException e) { //Shouldn't happen
            System.out.println("Failed to close database connection???");
            e.printStackTrace();
        }
    }

    @Override
    public void shutdownServer() {
        Bukkit.shutdown();
    }

    @Override
    public Logger logger() {
        return logger();
    }
}
