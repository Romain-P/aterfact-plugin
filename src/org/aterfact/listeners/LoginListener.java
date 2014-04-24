package org.aterfact.listeners;

import lombok.extern.slf4j.Slf4j;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;

@Slf4j
public class LoginListener implements Listener {
    @EventHandler
    public void playerConnected(PlayerLoginEvent event) {

        log.info("player "+ event.getPlayer().getName() +" connected");
    }

    @EventHandler
    public void playerDisconnected(PlayerKickEvent event) {

        log.info("player "+ event.getPlayer().getName() +" disconnected: "
                + event.getReason());
    }
}
