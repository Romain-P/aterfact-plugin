package org.aterfact.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.aterfact.core.injector.DefaultModule;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.aterfact.listeners.injector.ListenerModule;
import org.aterfact.database.Database;
import org.aterfact.database.injector.DatabaseModule;
import org.slf4j.MarkerFactory;

import java.util.Set;

@Slf4j
public class Aterfact extends JavaPlugin {
    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new DefaultModule(this), new ListenerModule(), new DatabaseModule());
        log.info("loading config..");
        injector.getInstance(Config.class).initialize();

        log.info("initializing database..");
        injector.getInstance(Database.class).initializeConnection();
        log.info("database initialized with success!");

        log.info("launching event listeners");
        Set<Listener> listeners = injector.getInstance(Set.class);

        for(Listener listener: listeners)
            getServer().getPluginManager().registerEvents(listener, this);

        log.info(MarkerFactory.getMarker("WARM"), "aterfact anable!");
    }

    @Override
    public void onDisable() {
        log.info(MarkerFactory.getMarker("WARM"), "aterfact disable!");
    }
}
