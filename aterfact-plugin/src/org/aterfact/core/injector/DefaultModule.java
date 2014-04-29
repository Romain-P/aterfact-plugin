/*
 * Created by IntelliJ IDEA.
 * User: Return
 * Date: 24/04/14
 * Time: 11:47
 */
package org.aterfact.core.injector;

import com.google.inject.AbstractModule;
import org.aterfact.core.Config;
import org.aterfact.objects.ServerHandler;
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
