package com.github.romainp.aterfact.database;

import com.github.romainp.aterfact.core.Config;
import com.google.inject.Inject;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    @Getter private Connection connection;
    @Inject
    Config config;
    @Inject JavaPlugin plugin;

    public void initializeConnection() {
        try {
            connection = DriverManager.getConnection(
                    config.getDatabaseUrl(),
                    config.getDatabaseUser(),
                    config.getDatabasePass());
            connection.setAutoCommit(true);

            if(connection.isValid(1)) {
                plugin.getLogger().info("database initialized!");
            }
        } catch(SQLException e) {
            plugin.getLogger().warning("Can't connect to database: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if(!connection.isClosed())
                connection.close();
        } catch(Exception e) {}
    }
}