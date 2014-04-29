package org.aterfact.database.managers;

import com.google.inject.Inject;
import org.aterfact.database.Manager;
import org.aterfact.objects.ServerHandler;
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
            statement.setBoolean(3, server.isOnline());
            statement.setInt(4, server.getPlayers().size());
            statement.setString(5, server.getUptime());
            execute(statement);
        } catch(Exception e) {
            plugin.getLogger().warning("sql error: "+e);
        }
    }
}
