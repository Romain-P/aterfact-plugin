package com.github.romainp.aterfact.listeners.injector;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.github.romainp.aterfact.listeners.FightListener;
import org.bukkit.event.Listener;
import com.github.romainp.aterfact.listeners.LoginListener;

public class ListenerModule extends AbstractModule {
    protected void configure() {
        Multibinder<Listener> binder = Multibinder.newSetBinder(binder(), Listener.class);
        binder.addBinding().toInstance(new LoginListener());
        binder.addBinding().toInstance(new FightListener());
    }
}
