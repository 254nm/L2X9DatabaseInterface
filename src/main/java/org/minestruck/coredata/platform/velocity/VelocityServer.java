package org.minestruck.coredata.platform.velocity;

import lombok.RequiredArgsConstructor;
import org.minestruck.coredata.platform.MCServer;

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
}
