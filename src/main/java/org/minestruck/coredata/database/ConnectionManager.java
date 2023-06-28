package org.minestruck.coredata.database;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.minestruck.coredata.MCServer;
import org.minestruck.coredata.database.connections.MySQL;
import org.minestruck.coredata.database.connections.Redis;

/**
 * @author 254n_m
 * @since 2023/06/27 9:17 PM
 * This file was created as a part of CoreData
 */
@Getter
@Accessors(fluent = true)
public class ConnectionManager implements AutoCloseable {
    private final MySQL mysql;
    private final Redis redis;

    public ConnectionManager(MCServer server) {
        mysql = new MySQL(getEnv("MYSQL_CONNECTION_STRING"), getEnv("MYSQL_USERNAME"), getEnv("MYSQL_PASSWORD"), server);
        redis = new Redis(getEnv("REDIS_HOST"), Integer.parseInt(getEnv("REDIS_PORT")), getEnv("REDIS_USER"), getEnv("REDIS_PASS"), server);
    }

    private static String getEnv(String name) {
        String value = System.getenv(name);
        if (value == null) throw new RuntimeException("Environment variable " + name + " is not set!");
        return value;
    }

    @Override
    public void close() throws Exception {
        mysql.close();
    }
}
