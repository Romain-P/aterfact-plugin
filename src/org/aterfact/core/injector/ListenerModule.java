/*
 * Created by IntelliJ IDEA.
 * User: Return
 * Date: 24/04/14
 * Time: 00:03
 */
package org.aterfact.core.injector;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.aterfact.listeners.LoginListener;

public class ListenerModule extends AbstractModule {
    private JavaPlugin plugin;

    public ListenerModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    protected void configure() {
        bind(JavaPlugin.class).toInstance(plugin);

        Multibinder<Listener> binder = Multibinder.newSetBinder(binder(), Listener.class);
        binder.addBinding().toInstance(new LoginListener());
    }
}
