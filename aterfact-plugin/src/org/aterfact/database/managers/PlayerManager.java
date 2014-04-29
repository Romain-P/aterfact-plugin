package org.aterfact.database.managers;

import com.google.inject.Inject;
import org.aterfact.core.Config;
import org.aterfact.database.Manager;
import org.aterfact.objects.ClientPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerManager extends Manager {
    @Inject JavaPlugin plugin;
    @Inject Config config;

	public void create(ClientPlayer player) {
        try {
            PreparedStatement statement = createStatement(
                    "INSERT INTO players(uuid, name, kills, deaths, connected)" +
                            " VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, player.getUuid());
            statement.setString(2, player.getName());
            statement.setInt(3, player.getKills());
            statement.setInt(4, player.getDeaths());
            statement.setBoolean(5, player.isConnected());
            execute(statement);
        } catch(Exception e) {
            plugin.getLogger().warning("sql error: "+e);
        }
	}

    public ClientPlayer load(String attribute) {
        ClientPlayer player = null;
        try {
            ResultSet result = getData("SELECT * FROM players WHERE "+config.getAttribute()+" = '"+attribute+"';");

            if(result.next()) {
                player = new ClientPlayer(
                    result.getString("uuid"),
                    result.getString("name"),
                    result.getInt("kills"),
                    result.getInt("deaths"));
            }
            closeResultSet(result);
        } catch(Exception e) {
            plugin.getLogger().warning("sql error: "+e);
        }
        return player;
    }

    public void reload(ClientPlayer player) {
        try {
            ResultSet result = getData("SELECT * FROM players WHERE "+config.getAttribute()+" = '"+player.getAttribute()+"';");

            if(result.next()) {
                player.setUuid(result.getString("uuid"));
                player.setName(result.getString("name"));
                player.setKills(result.getInt("kills"));
                player.setDeaths(result.getInt("deaths"));
            }
            closeResultSet(result);
        } catch(Exception e) {
            plugin.getLogger().warning("sql error: "+e);
        }
    }

    public void update(ClientPlayer player) {
        try {
            PreparedStatement statement = createStatement(
                    "UPDATE players SET uuid = ?, name = ?, kills = ?, deaths = ?, connected = ? WHERE "+config.getAttribute()+" = ?");
            statement.setString(1, player.getUuid());
            statement.setString(2, player.getName());
            statement.setInt(3, player.getKills());
            statement.setInt(4, player.getDeaths());
            statement.setBoolean(5, player.isConnected());
            statement.setString(6, player.getAttribute());
            execute(statement);
        } catch(Exception e) {
            plugin.getLogger().warning("sql error: "+e);
        }
    }
}
