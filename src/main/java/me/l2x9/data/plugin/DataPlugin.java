package me.l2x9.data.plugin;

import me.l2x9.data.platform.MCServer;
import me.l2x9.data.database.ConnectionManager;
import me.l2x9.data.platform.Platform;
import me.l2x9.data.platform.bukkit.BukkitMain;
import me.l2x9.data.platform.velocity.VelocityMain;
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
