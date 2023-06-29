package me.l2x9.data.database;

import lombok.Getter;
import lombok.experimental.Accessors;
import me.l2x9.data.config.DataConfig;
import me.l2x9.data.database.connections.MySQL;
import me.l2x9.data.database.connections.Redis;
import me.l2x9.data.platform.MCServer;

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
        DataConfig config = server.config();
        mysql = new MySQL(config.get("MySQL.ConnectionString"), config.get("MySQL.Username"), config.get("MySQL.Password"), server);
        redis = new Redis(config.get("Redis.Host"), config.getInt("Redis.Port"), config.get("Redis.Username"), config.get("Redis.Password"), server);
    }


    @Override
    public void close() throws Exception {
        mysql.close();
        redis.close();
    }
}
