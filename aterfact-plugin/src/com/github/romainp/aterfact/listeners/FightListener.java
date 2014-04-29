package com.github.romainp.aterfact.listeners;

import com.github.romainp.aterfact.core.Config;
import com.github.romainp.aterfact.objects.ClientPlayer;
import com.github.romainp.aterfact.objects.ServerHandler;
import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FightListener implements Listener {
    @Inject
    ServerHandler server;
    @Inject
    Config config;

    @EventHandler
    public void playerDied(PlayerDeathEvent event) {
        ClientPlayer client = server.getPlayerByEvent(event);
        client.addDeath();

        ClientPlayer killer = server.getPlayerByEntity(event.getEntity().getKiller());
        killer.addKill();

        if(config.isUseMessages()) {
            String message = "Vous etes desormais a "+client.getKills()+" kills et "+client.getDeaths()+" morts.";
            client.sendMessage(message);
            killer.sendMessage(message);
        }
    }
}
