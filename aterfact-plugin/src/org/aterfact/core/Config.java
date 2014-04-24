package org.aterfact.core;

import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class Config {
    private com.typesafe.config.Config config;
    @Getter private String databaseHost;
    @Getter private String databaseName;
    @Getter private String databaseUser;
    @Getter private String databasePass;

    public Config() {
        this.config = ConfigFactory.parseFile(new File("aterfact/config.conf"));
    }

    public void initialize() {
        if(config.isEmpty()) {
            log.error("aterfact can't load \"aterfact/config.conf\"");
            System.exit(1);
        }
        this.databaseHost = config.getString("aterfact.database.host");
        this.databaseName = config.getString("aterfact.database.name");
        this.databaseUser = config.getString("aterfact.database.user");
        this.databasePass = config.getString("aterfact.database.pass");
    }
}
