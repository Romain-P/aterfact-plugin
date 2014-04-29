package org.aterfact.core;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.aterfact.core.injector.DefaultModule;
import org.aterfact.database.Database;
import org.aterfact.objects.ServerHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.aterfact.listeners.injector.ListenerModule;
import org.aterfact.database.injector.DatabaseModule;

import java.util.Set;

public class Aterfact extends JavaPlugin {
    @Inject Database database;
    @Inject Set<Listener> listeners;

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new DefaultModule(this), new ListenerModule(), new DatabaseModule());
        getLogger().info("loading config..");
        injector.getInstance(Config.class).initialize();

        getLogger().info("initializing database..");
        injector.getInstance(Database.class).initializeConnection();
        getLogger().info("database initialized with success!");

        getLogger().info("launching event listeners");

        for(Listener listener: listeners)
            getServer().getPluginManager().registerEvents(listener, this);

        getLogger().info("launching server handler..");
        injector.getInstance(ServerHandler.class).initialize();

        getLogger().info("aterfact plugin anabled!");
    }

    @Override
    public void onDisable() {
        database.closeConnection();
        getLogger().info("aterfact plugin disabled!");
    }
}
