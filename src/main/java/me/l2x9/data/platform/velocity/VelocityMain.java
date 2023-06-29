package me.l2x9.data.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Cleanup;
import lombok.Getter;
import lombok.experimental.Accessors;
import me.l2x9.data.config.DataConfig;
import me.l2x9.data.config.configimpl.VelocityConfig;
import me.l2x9.data.database.ConnectionManager;
import me.l2x9.data.plugin.DataPlugin;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@Plugin(id = "l2x9datainterface", name = "L2X9DataInterface", version = "1.1-SNAPSHOT", authors = {"254n_m"})
public class VelocityMain implements DataPlugin {
    private static VelocityMain instance;
    private final ProxyServer proxy;
    private final Logger logger;
    private final VelocityServer server = new VelocityServer(this);
    private Map<String, Object> config;
    private final ConnectionManager connectionManager;

    @Inject
    public VelocityMain(ProxyServer proxy, Logger logger) {
        this.proxy = proxy;
        this.logger = logger;
        try {
            loadConfig();
        } catch (Throwable e) {
            logger().error("Failed to load config", e);
            e.printStackTrace();
        }
        connectionManager = new ConnectionManager(server);
        instance = this;
    }

    public static VelocityMain getInstance() {
        return instance;
    }

    @Subscribe
    public void onShutdown(ProxyShutdownEvent event) {
        this.onDisable();
    }

    public DataConfig getConfig() {
        return new VelocityConfig(config);
    }

    private void loadConfig() throws Throwable {
        File configFile = new File(getPluginDataFolder(), "config.yml");
        if (!configFile.exists()) {
            @Cleanup InputStream is = getClass().getClassLoader().getResourceAsStream("config.yml");
            if (is == null) throw new NullPointerException("Missing resource config.yml");
            Files.copy(is, configFile.toPath());
        }
        @Cleanup FileInputStream configInputStream = new FileInputStream(configFile);
        Yaml snakeyaml = new Yaml(new SafeConstructor());
        config = snakeyaml.load(configInputStream);
    }

    private File getPluginDataFolder() {
        File dataFolder = new File("plugins", getClass().getAnnotation(Plugin.class).id());
        if (!dataFolder.exists()) dataFolder.mkdirs();
        return dataFolder;
    }
}
