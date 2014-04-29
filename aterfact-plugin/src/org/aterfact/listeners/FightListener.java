package org.aterfact.listeners;

import com.google.inject.Inject;
import org.aterfact.core.Config;
import org.aterfact.objects.ClientPlayer;
import org.aterfact.objects.ServerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FightListener implements Listener {
    @Inject ServerHandler server;
    @Inject Config config;

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
