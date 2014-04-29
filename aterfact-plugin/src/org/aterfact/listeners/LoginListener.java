package org.aterfact.listeners;

import com.google.inject.Inject;
import org.aterfact.core.Config;
import org.aterfact.database.managers.PlayerManager;
import org.aterfact.objects.ClientPlayer;
import org.aterfact.objects.ServerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginListener implements Listener {
    @Inject ServerHandler server;
    @Inject Config config;
    @Inject PlayerManager manager;

    @EventHandler
    public void playerConnected(PlayerLoginEvent event) {
        ClientPlayer client = generateByEvent(event);

        if(config.isUseBungee())
            manager.reload(client);

        client.setConnected(true);
        client.save();
    }

    @EventHandler
    public void playerDisconnected(PlayerKickEvent event) {
        ClientPlayer client = generateByEvent(event);
        client.setConnected(false);
        client.save();
    }

    private ClientPlayer generateByEvent(PlayerEvent event) {
        Player player = event.getPlayer();
        String attribute = config.isUseUuid() ? player.getUniqueId().toString() : player.getName();

        return server.getPlayer(attribute);
    }
}
