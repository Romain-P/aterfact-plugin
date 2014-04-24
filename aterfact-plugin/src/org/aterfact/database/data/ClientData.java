package org.aterfact.database.data;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aterfact.database.AbstractDAO;
import org.aterfact.objects.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ClientData extends AbstractDAO<Client> {
    @Getter
    private Map<Integer, Client> data = new ConcurrentHashMap<>();

    @Override
    public boolean create(Client obj) {
        try {
            PreparedStatement statement = createStatement("INSERT INTO players(`UUID`, `name`, `adminLevel`, `bannedTime`) VALUES (?,?,?,?);");
            statement.setString(1, obj.getUUID());
            statement.setString(2, obj.getName());
            statement.setInt(3, obj.getAdminLevel());
            statement.setLong(4, obj.getBannedTime());
            execute(statement);
            return true;
        } catch(Exception e) {
            log.error("sql-error : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Client obj) {
        String query = "DELETE FROM players WHERE UUID = "+obj.getUUID();
        execute(query);
        return false;
    }

    @Override
    public boolean update(Client obj) {
        try {
            PreparedStatement statement = createStatement("UPDATE players SET name = ?, adminLevel = ?, bannedTime = ? WHERE UUID = ?;");
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getAdminLevel());
            statement.setLong(3, obj.getBannedTime());
            statement.setString(4, obj.getUUID());
            execute(statement);
            return true;
        } catch(Exception e) {
            log.error("sql-error : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Client load(String uuid) {
        Client player = null;
        try {
            ResultSet result = getData("SELECT * FROM players WHERE UUID = "+uuid);
            if(result.next()) {
                player = new Client(
                    result.getString("UUID"),
                    result.getString("name"),
                    result.getInt("adminLevel"),
                    result.getLong("bannedTime")
                );
            }
            closeResultSet(result);
        } catch(Exception e) {
            log.error("sql-error : " + e.getMessage());
        }
        return player;
    }

    @Override
    public Client load(int i) {return null;}
}
