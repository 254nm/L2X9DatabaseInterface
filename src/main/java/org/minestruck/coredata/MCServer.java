package org.minestruck.coredata;

/**
 * @author 254n_m
 * @since 2023/06/27 9:30 PM
 * This file was created as a part of CoreData
 */
public interface MCServer {
    void shutdownServer();
    void log(String format, Object... args);
}
