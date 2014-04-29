/*
 * Created by IntelliJ IDEA.
 * User: Return
 * Date: 24/04/14
 * Time: 11:47
 */
package com.github.romainp.aterfact.core.injector;

import com.github.romainp.aterfact.core.Config;
import com.github.romainp.aterfact.objects.ServerHandler;
import com.google.inject.AbstractModule;
import org.bukkit.plugin.java.JavaPlugin;

public class DefaultModule extends AbstractModule {
    private JavaPlugin plugin;

    public DefaultModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    protected void configure() {
        bind(JavaPlugin.class).toInstance(plugin);
        bind(ServerHandler.class).asEagerSingleton();
        bind(Config.class).asEagerSingleton();
    }
}
