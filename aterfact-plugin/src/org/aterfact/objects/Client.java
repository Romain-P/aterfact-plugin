package org.aterfact.objects;

import lombok.Getter;
import lombok.Setter;

public class Client {
    @Getter @Setter private String UUID;
    @Getter @Setter private String name;
    @Getter @Setter private int adminLevel;
    @Getter @Setter private long bannedTime;

    @Getter @Setter org.bukkit.entity.Player player;

    public Client(String UUID, String name, int adminLevel, long bannedTime) {
        this.UUID = UUID;
        this.name = name;
        this.adminLevel = adminLevel;
        this.bannedTime = bannedTime;
    }
}
