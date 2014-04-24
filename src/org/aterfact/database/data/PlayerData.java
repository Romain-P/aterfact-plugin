package org.aterfact.database.data;

import org.aterfact.database.AbstractDAO;
import org.aterfact.database.objects.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerData extends AbstractDAO<Player> {
    @Override
    public boolean create(Player obj) {
        try {
            PreparedStatement statement = createStatement("INSERT INTO players(`id`, `name`, `bannedTime`) VALUES (?,?,?);");
            statement.setString(1, obj.getName());
            statement.setLong(2, obj.getBannedTime());
            statement.setInt(3, obj.getId());
            execute(statement);
            return true;
        } catch(Exception e) {
            plugin.getLogger().severe("sql-error : "+ e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Player obj) {
        String query = "DELETE FROM players WHERE id = "+obj.getId();
        execute(query);
        return false;
    }

    @Override
    public boolean update(Player obj) {
        try {
            PreparedStatement statement = createStatement("UPDATE players SET name = ?, bannedTime = ? WHERE id = ?;");
            statement.setString(1, obj.getName());
            statement.setLong(2, obj.getBannedTime());
            statement.setInt(3, obj.getId());
            execute(statement);
            return true;
        } catch(Exception e) {
            plugin.getLogger().severe("sql-error : "+ e.getMessage());
        }
        return false;
    }

    @Override
    public Player load(int id) {
        Player player = null;
        try {
            ResultSet result = getData("SELECT * FROM players WHERE id = "+id);
            if(result.next()) {
                player = new Player(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getLong("bannedTime")
                );
            }
            closeResultSet(result);
        } catch(Exception e) {
            plugin.getLogger().severe("sql-error : " + e.getMessage());
        }
        return player;
    }
}
