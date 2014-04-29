package com.github.romainp.aterfact.objects;

import com.github.romainp.aterfact.core.Config;
import com.google.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import com.github.romainp.aterfact.database.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

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

    public ClientPlayer(String attribute) {
        if(config.isUseUuid()) {
            this.uuid = attribute;
            this.name = Bukkit.getServer().getPlayer(UUID.fromString(attribute)).getName();
        } else {
            this.uuid = "";
            this.name = attribute;
        }
    }

    @SuppressWarnings("deprecation")
    public void sendMessage(String message) {
        Player player = config.isUseUuid()
                ? Bukkit.getServer().getPlayer(UUID.fromString(this.uuid))
                : Bukkit.getServer().getPlayer(this.name);
        player.sendMessage(message);
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
