package com.github.romainp.aterfact.listeners;

import com.google.inject.Inject;
import com.github.romainp.aterfact.core.Config;
import com.github.romainp.aterfact.database.managers.PlayerManager;
import com.github.romainp.aterfact.objects.ClientPlayer;
import com.github.romainp.aterfact.objects.ServerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {
    @Inject ServerHandler server;
    @Inject Config config;
    @Inject PlayerManager manager;

    @EventHandler
    public void playerConnected(PlayerLoginEvent event) {
        ClientPlayer client = server.getPlayerByEvent(event);

        if(config.isUseBungee())
            manager.reload(client);

        client.setConnected(true);
        client.save();
    }

    @EventHandler
    public void playerDisconnected(PlayerKickEvent event) {
        ClientPlayer client = server.getPlayerByEvent(event);
        client.setConnected(false);
        client.save();
    }
}
