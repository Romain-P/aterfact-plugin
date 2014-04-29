package org.aterfact.objects;

import com.google.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.aterfact.core.Config;
import org.aterfact.database.managers.PlayerManager;

public class ClientPlayer {
    @Getter @Setter private String uuid, name;
    @Getter @Setter private int deaths, kills;
    @Getter @Setter private boolean connected;

    @Inject PlayerManager manager;
    @Inject Config config;

    public ClientPlayer(String uuid, String name, int kills, int deaths) {
        this.uuid = uuid;
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
    }

    public ClientPlayer(String name) {
        this.uuid = "";
        this.name = name;
    }

    public String getAttribute() {
        return config.isUseUuid() ? this.uuid : this.name;
    }

    public void addKill() {
        this.kills++;
        save();
    }

    public void addDeath() {
        this.deaths++;
        save();
    }

    public void save() {
        this.manager.update(this);
    }
}
