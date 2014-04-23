package org.minplug.plugin;

import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginListener implements Listener {
    @Inject JavaPlugin plugin;

    @EventHandler
    public void onPlayerConnected(PlayerLoginEvent event) {

    }
}
