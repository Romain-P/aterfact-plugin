package com.github.romainp.aterfact.database.injector;

import com.github.romainp.aterfact.database.managers.ServerManager;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.github.romainp.aterfact.database.Database;
import com.github.romainp.aterfact.database.Manager;
import com.github.romainp.aterfact.database.managers.PlayerManager;

import java.util.concurrent.locks.ReentrantLock;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).asEagerSingleton();
        bind(ReentrantLock.class).asEagerSingleton();
        Multibinder<Manager> binder = Multibinder.newSetBinder(binder(), Manager.class);
        binder.addBinding().toInstance(new PlayerManager());
        binder.addBinding().toInstance(new ServerManager());
    }
}
