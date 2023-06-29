package me.l2x9.data.platform.bukkit;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import me.l2x9.data.config.DataConfig;
import me.l2x9.data.config.configimpl.BukkitConfig;
import me.l2x9.data.platform.MCServer;
import org.slf4j.Logger;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BukkitServer implements MCServer {
    private final BukkitMain plugin;

    @Override
    @SuppressWarnings("unchecked")
    public <S> S getHandle() {
        return (S) plugin.getServer();
    }

    @Override
    public void shutdown() {
        plugin.getServer().shutdown();
    }

    @Override
    public Logger logger() {
        return plugin.getSLF4JLogger();
    }

    @Override
    public DataConfig config() {
        return new BukkitConfig(plugin.getConfig());
    }
}
