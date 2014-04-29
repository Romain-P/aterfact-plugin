package org.aterfact.objects;

import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.Getter;
import org.aterfact.core.Config;
import org.aterfact.database.managers.PlayerManager;
import org.aterfact.database.managers.ServerManager;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerHandler {
    @Getter private String name;
    @Getter private int port;
    @Getter private boolean online;
    @Getter private long startedTime;
    private ScheduledExecutorService worker;

    @Getter private Map<String, ClientPlayer> players;

    @Inject Config config;
    @Inject ServerManager manager;
    @Inject PlayerManager playerManager;
    @Inject Injector injector;

    public ServerHandler() {
        this.startedTime = System.currentTimeMillis()/1000;
        this.online = true;
        this.worker = Executors.newSingleThreadScheduledExecutor();
        this.players = new ConcurrentHashMap<>();
    }

    public void initialize() {
        this.name = config.getServerName();
        this.port = Bukkit.getServer().getPort();
        this.worker.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                update();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public void close() {
        this.worker.shutdown();

        this.online = false;
        this.players.clear();
        this.startedTime = -1;
        update();
    }

    public ClientPlayer getPlayer(String attribute, boolean create) {
        ClientPlayer player = this.players.get(attribute);
        if(player == null) {
            player = playerManager.load(attribute);

            if(player == null && create)
                player = createPlayer(attribute);
            if(player != null) {
                injector.injectMembers(player);
                this.players.put(attribute, player);
            }
        }
        return player;
    }

    public ClientPlayer getPlayer(String attribute) {
        return getPlayer(attribute, false);
    }

    private ClientPlayer createPlayer(String name) {
        ClientPlayer player = new ClientPlayer(name);
        playerManager.create(player);
        return player;
    }

    public String getUptime() {
        if(this.startedTime == -1)
            return "offline";

        double uptime = (System.currentTimeMillis()/1000 - startedTime);
        double days = 0, hours = 0, minutes = 0;

        final int secondsInDay = 24*60*60*60;
        final int secondsInHour = 1*60*60;
        final int secondsInMinute = 60;

        days = uptime / secondsInDay;
        uptime %= secondsInDay;
        hours = uptime / secondsInHour;
        uptime %= secondsInHour;
        minutes = uptime / secondsInMinute;

        return (int)days+"j "+(int)hours+"h "+(int)minutes+"min";
    }

    public void update() {
        manager.update(this);
    }
}
