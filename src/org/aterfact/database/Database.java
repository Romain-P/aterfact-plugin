package org.aterfact.database;

import com.google.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aterfact.core.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class Database {
    @Getter Connection connection;
    @Inject Set<DAO> objects;
    @Inject Config config;

    @Getter
    private Map<Class, DAO> data = new HashMap<>();

    public void initializeConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                    config.getDatabaseHost() + "/" +
                    config.getDatabaseName(),
                    config.getDatabaseUser(),
                    config.getDatabasePass());
            connection.setAutoCommit(true);
            initializeData();
        } catch(Exception e) {
            log.error("Can't connect to database: " + e.getMessage());
            System.exit(1);
        }
    }

    public void initializeData() {
        for(DAO data: objects)
            this.data.put(data.getClass(), data);
        log.info(data.size() + " dao-objects loaded");
    }
}
