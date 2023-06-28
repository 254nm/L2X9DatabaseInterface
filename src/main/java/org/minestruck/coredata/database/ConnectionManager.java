package org.minestruck.coredata.database;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.minestruck.coredata.MCServer;
import org.minestruck.coredata.database.connections.MySQL;

/**
 * @author 254n_m
 * @since 2023/06/27 9:17 PM
 * This file was created as a part of CoreData
 */
@Getter
@Accessors(fluent = true)
public class ConnectionManager {
    private final MySQL mysql;
    public ConnectionManager(MCServer server) {
        mysql = new MySQL(System.getenv("MYSQL_CONNECTION_STRING"), System.getenv("MYSQL_USERNAME"), System.getenv("MYSQl_PASSWORD"), server);
    }
}
