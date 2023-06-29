package me.l2x9.data.config.configimpl;

import lombok.RequiredArgsConstructor;
import me.l2x9.data.config.DataConfig;

import org.bukkit.configuration.Configuration;

/**
 * @author 254n_m
 * @since 2023/06/29 9:13 AM
 * This file was created as a part of CoreData
 */
@RequiredArgsConstructor
public class BukkitConfig implements DataConfig {
    private final Configuration bukkitConfig;
    @Override
    public <T> T get(String key) {
        return (T) bukkitConfig.get(key);
    }

    @Override
    public int getInt(String key) {
        return bukkitConfig.getInt(key);
    }
}
