package me.l2x9.data.config;

/**
 * @author 254n_m
 * @since 2023/06/29 9:11 AM
 * This file was created as a part of CoreData
 */
public interface DataConfig {
    <T> T get(String key);
    int getInt(String key);
}
