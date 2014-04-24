package org.aterfact.database.objects;

import lombok.Getter;
import lombok.Setter;

public class Player {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private long bannedTime;

    public Player(int id, String name, long bannedTime) {
        this.id = id;
        this.name = name;
        this.bannedTime = bannedTime;
    }
}
