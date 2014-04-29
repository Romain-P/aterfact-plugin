package org.aterfact.database.injector;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.aterfact.database.Database;
import org.aterfact.database.Manager;
import org.aterfact.database.managers.PlayerManager;
import org.aterfact.database.managers.ServerManager;

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
