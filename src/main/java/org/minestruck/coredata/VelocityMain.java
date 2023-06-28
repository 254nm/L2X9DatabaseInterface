package org.minestruck.coredata;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.minestruck.coredata.database.ConnectionManager;
import org.slf4j.Logger;

@Plugin(id = "minestruckcoredata", name = "MineStruckCoreData", version = "1.0-SNAPSHOT", authors = {"254n_m"})
public final class VelocityMain implements MCServer {
    private final ProxyServer server;
    private final Logger logger;
    private static ConnectionManager connectionManager;
    @Inject
    public VelocityMain(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        connectionManager = new ConnectionManager(this);
    }
    @Subscribe
    public void onShutdown(ProxyShutdownEvent event) {
        try {
            connectionManager.close();
        } catch (Throwable t) { //Shouldn't happen
            System.out.println("Failed to close database connection???");
            t.printStackTrace();
        }
    }

    @Override
    public void shutdownServer() {
        server.shutdown();
    }

    @Override
    public Logger logger() {
        return logger;
    }
}
