package org.minestruck.coredata.platform.bukkit;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.minestruck.coredata.platform.MCServer;

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
}
