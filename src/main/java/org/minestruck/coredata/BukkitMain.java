package org.minestruck.coredata;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.minestruck.coredata.database.ConnectionManager;

import java.sql.SQLException;
import java.util.logging.Level;

@Accessors(fluent = true)
public final class BukkitMain extends JavaPlugin implements MCServer {
   @Getter private static ConnectionManager connectionManager;

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
    public void log(String format, Object... args) {
        getLogger().log(Level.INFO, String.format(format, args));
    }
}
