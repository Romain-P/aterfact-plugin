package org.aterfact.listeners;

import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginListener implements Listener {
    @Inject JavaPlugin plugin;

    @EventHandler
    public void playerConnected(PlayerLoginEvent event) {

        plugin.getLogger().info("player "+ event.getPlayer().getName() +" connected");
    }

    @EventHandler
    public void playerDisconnected(PlayerKickEvent event) {

        plugin.getLogger().info("player "+ event.getPlayer().getName() +" disconnected: "
                + event.getReason());
    }
}
