package org.aterfact.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.aterfact.core.injector.ListenerModule;
import org.aterfact.database.Database;
import org.aterfact.database.injector.DatabaseModule;

import java.util.Set;

public class Aterfact extends JavaPlugin {
    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new ListenerModule(this), new DatabaseModule());

        getLogger().severe("initializing database..");
        injector.getInstance(Database.class).initializeConnection();
        getLogger().severe("database initialized with success!");

        getLogger().severe("launching event listeners");
        Set<Listener> listeners = injector.getInstance(Set.class);

        for(Listener listener: listeners)
            getServer().getPluginManager().registerEvents(listener, this);

        getLogger().severe("aterfact enable!");
    }

    @Override
    public void onDisable() {
        getLogger().severe("aterfact is now disable!");
    }
}
