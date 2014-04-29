package org.aterfact.core;

import com.google.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    @Inject private JavaPlugin plugin;
    @Getter private String databaseHost;
    @Getter private String databaseName;
    @Getter private String databaseUser;
    @Getter private String databasePass;
    @Getter private String databaseUrl;
    @Getter private String serverName;
    @Getter private boolean useUuid, useBungee, useMessages;
    @Getter private String attribute;

    public void initialize() {
        this.databaseHost = plugin.getConfig().getString("database.host");
        this.databaseName = plugin.getConfig().getString("database.name");
        this.databaseUser = plugin.getConfig().getString("database.user");
        this.databasePass = plugin.getConfig().getString("database.pass");
        this.databaseUrl = "jdbc:mysql://"+databaseHost+"/"+databaseName;
        this.serverName = plugin.getConfig().getString("server.name");
        this.useUuid = plugin.getConfig().getBoolean("server.uuid");
        this.useBungee = plugin.getConfig().getBoolean("server.bungee");
        this.useMessages = plugin.getConfig().getBoolean("server.messages");
        this.attribute = useUuid ? "uuid" : "name";
    }
}
