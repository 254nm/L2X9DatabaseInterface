package org.minestruck.coredata.plugin;

import org.minestruck.coredata.platform.MCServer;
import org.minestruck.coredata.database.ConnectionManager;
import org.minestruck.coredata.platform.Platform;
import org.minestruck.coredata.platform.bukkit.BukkitMain;
import org.minestruck.coredata.platform.velocity.VelocityMain;
public interface DataPlugin {
    MCServer server();


    ConnectionManager connectionManager();

    default void onDisable() {
        try {
            connectionManager().close();
        } catch (Throwable t) { //Shouldn't happen
            server().logger().error("Failed to close database connection(s)", t);
        }
    }

    static DataPlugin get() {
        if (Platform.get() == Platform.BUKKIT) {
            return BukkitMain.getInstance();
        } else if (Platform.get() == Platform.VELOCITY) {
            return VelocityMain.getInstance();
        }
        return null;
    }
}
