package me.l2x9.data.config.configimpl;

import lombok.RequiredArgsConstructor;
import me.l2x9.data.config.DataConfig;

import java.util.Map;

/**
 * @author 254n_m
 * @since 2023/06/29 9:15 AM
 * This file was created as a part of CoreData
 */
@RequiredArgsConstructor
public class VelocityConfig implements DataConfig {
    private final Map<String, Object> config;

    @Override
    public <T> T get(String key) {
        return (T) traversePath(key).get(key.substring(key.lastIndexOf('.')+1));
    }

    @Override
    public int getInt(String key) {
        return (Integer) traversePath(key).get(key.substring(key.lastIndexOf('.')+1));
    }

    private Map<String, Object> traversePath(String key) {
        String[] path = key.split("\\.");
        Map<String, Object> currMap = config;
        for (int i = 0; i < path.length - 1; i++) {
            currMap = (Map<String, Object>) config.get(path[i]);
        }
        return currMap;
    }
}
