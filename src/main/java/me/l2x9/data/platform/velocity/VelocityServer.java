package me.l2x9.data.platform.velocity;

import lombok.RequiredArgsConstructor;
import me.l2x9.data.config.DataConfig;
import me.l2x9.data.platform.MCServer;
import org.slf4j.Logger;

@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class VelocityServer implements MCServer {
    private final VelocityMain plugin;

    @Override
    @SuppressWarnings("unchecked")
    public <S> S getHandle() {
        return (S) plugin.proxy();
    }

    @Override
    public void shutdown() {
        plugin.proxy().shutdown();
    }

    @Override
    public Logger logger() {
        return plugin.logger();
    }

    @Override
    public DataConfig config() {
        return plugin.getConfig();
    }
}
