package org.aterfact.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class World {
    @Getter @Setter private String UUID;
    @Getter @Setter private String name;
    @Getter @Setter private int adminLevel;
    @Getter private int connectedPlayers;
    @Getter @Setter private int maxConnectedPlayers;

    public World(String UUID, String name, int adminLevel) {
        this.UUID = UUID;
        this.name = name;
        this.adminLevel = adminLevel;
    }

    public void updateConnectedPlayers() {
        int connected = 0;

        for(Player player: Bukkit.getWorld(java.util.UUID.fromString(UUID)).getPlayers())
            if(player.isOnline())
                connected++;

        this.connectedPlayers = connected;
        if(connectedPlayers > maxConnectedPlayers)
            maxConnectedPlayers = connectedPlayers;
    }
}
