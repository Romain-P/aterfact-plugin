package org.minplug.database;

import com.google.inject.Inject;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Database {
    @Getter Connection connection;
    @Inject JavaPlugin plugin;
    @Inject Set<DAO> objects;

    @Getter
    private Map<Class, DAO> data = new HashMap<>();

    public void initializeConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                    plugin.getConfig().getString("minplug.database.host") + "/" +
                    plugin.getConfig().getString("minplug.database.name"),
                    plugin.getConfig().getString("minplug.database.user"),
                    plugin.getConfig().getString("minplug.database.pass"));
            connection.setAutoCommit(true);
            initializeData();
        } catch(Exception e) {
            plugin.getLogger().severe("Can't connect to database: " + e.getMessage());
            System.exit(1);
        }
    }

    public void initializeData() {
        for(DAO data: objects)
            this.data.put(data.getClass(), data);
        plugin.getLogger().info(data.size() + " dao-objects loaded");
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            plugin.getLogger().severe("Sql error: " + e.getMessage());
            System.exit(1);
        }
    }
}
