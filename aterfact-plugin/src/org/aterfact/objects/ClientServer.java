package org.aterfact.objects;

import com.google.inject.Inject;
import lombok.Getter;
import org.aterfact.core.Config;
import org.aterfact.database.managers.ServerManager;
import org.bukkit.Bukkit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientServer {
    @Getter private String name;
    @Getter private int port;
    @Getter private int players;
    @Getter private boolean online;
    @Getter private long startedTime;
    private ScheduledExecutorService worker;

    @Inject Config config;
    @Inject ServerManager manager;

    public ClientServer() {
        this.startedTime = System.currentTimeMillis()/1000;
        this.online = true;
        this.worker = Executors.newSingleThreadScheduledExecutor();
    }

    public void initialize() {
        this.name = config.getServerName();
        this.port = Bukkit.getServer().getPort();
        this.worker.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                players = Bukkit.getServer().getOnlinePlayers().length;
                update();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public void close() {
        this.worker.shutdown();

        this.online = false;
        this.players = 0;
        this.startedTime = -1;
        update();
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
