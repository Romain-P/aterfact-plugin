package org.aterfact.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

public class Client {
    @Getter @Setter private String UUID;
    @Getter @Setter private String name;
    @Getter @Setter private int adminLevel;
    @Getter @Setter private long bannedTime;

    private org.bukkit.entity.Player player;

    public Client(String UUID, String name, int adminLevel, long bannedTime) {
        this.UUID = UUID;
        this.name = name;
        this.adminLevel = adminLevel;
        this.bannedTime = bannedTime;
    }

    public org.bukkit.entity.Player getPlayer() {
        if(this.player == null || !player.isOnline())
            for(org.bukkit.entity.Player player: Bukkit.getServer().getOnlinePlayers())
                if(player.getUniqueId().equals(this.UUID)) {
                    this.player = player;
                    break;
                }
        return this.player;
    }
}
