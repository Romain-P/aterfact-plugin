package org.minplug.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.minplug.core.injector.DefaultModule;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class Main extends JavaPlugin {
    @Override
    @SuppressWarnings("unchecked")
    public void onEnable() {
        Injector injector = Guice.createInjector(new DefaultModule(this));
        Set<Listener> listeners = injector.getInstance(Set.class);

        for(Listener listener: listeners)
            getServer().getPluginManager().registerEvents(listener, this);

        getLogger().severe("minplug is now enable!");
    }

    @Override
    public void onDisable() {
        getLogger().severe("minplug is now disable!");
    }
}
