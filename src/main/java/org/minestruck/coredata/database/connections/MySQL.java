package org.minestruck.coredata.database.connections;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.minestruck.coredata.platform.MCServer;
import org.minestruck.coredata.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @author 254n_m
 * @since 2023/06/27 9:12 PM
 * This file was created as a part of CoreData
 */

@Accessors(fluent = true)
public class MySQL implements Database {
    @Getter
    private Connection connection;

    public MySQL(String connectionString, String username, String password, MCServer server) {
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            ResultSet result = connection.createStatement().executeQuery("SELECT CURRENT_DATE()");
            result.next();
            server.logger().info("Successfully connected to MySQL server! MySQL test query: {}", result.getString(1));
        } catch (Throwable t) {
            server.logger().error("Failed to connect to mysql database due to {} please see stacktrace below for more info!", t.getClass().getSimpleName());
            t.printStackTrace();
            server.logger().error("Failed to connect to mysql database due to %s please see stacktrace below for more info!", t);
            server.shutdownServer();
        }
    }


    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
