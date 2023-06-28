package org.minestruck.coredata.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.minestruck.coredata.platform.MCServer;
import org.minestruck.coredata.database.ConnectionManager;
import org.minestruck.coredata.plugin.DataPlugin;
import org.slf4j.Logger;

@Getter
@Accessors(fluent = true)
@Plugin(id = "minestruckcoredata", name = "MineStruckCoreData", version = "1.0-SNAPSHOT", authors = {"254n_m"})
public class VelocityMain implements DataPlugin {
    private static VelocityMain instance;
    private final ProxyServer proxy;
    private final Logger logger;
    private final VelocityServer server = new VelocityServer(this);
    private ConnectionManager connectionManager;

    @Inject
    public VelocityMain(ProxyServer proxy, Logger logger) {
        this.proxy = proxy;
        this.logger = logger;
        connectionManager = new ConnectionManager(server);
        instance = this;
    }
    @Subscribe
    public void onShutdown(ProxyShutdownEvent event) {
        this.onDisable();
    }

    public static VelocityMain getInstance() {
        return instance;
    }
}
