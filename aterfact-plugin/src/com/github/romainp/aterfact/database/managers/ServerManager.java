package com.github.romainp.aterfact.database.managers;

import com.google.inject.Inject;
import com.github.romainp.aterfact.database.Manager;
import com.github.romainp.aterfact.objects.ServerHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;

public class ServerManager extends Manager {
    @Inject JavaPlugin plugin;

    public void update(ServerHandler server) {
        try {
            PreparedStatement statement = createStatement(
                    "REPLACE INTO servers(name, port, online, players, uptime) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, server.getName());
            statement.setInt(2, server.getPort());
            statement.setString(3, String.valueOf(server.isOnline()));
            statement.setInt(4, Bukkit.getServer().getOnlinePlayers().length);
            statement.setString(5, server.getUptime());
            execute(statement);
        } catch(Exception e) {
            plugin.getLogger().warning("sql error: "+e);
        }
    }
}
