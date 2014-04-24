package org.minplug.database.injector;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.minplug.database.DAO;
import org.minplug.database.Database;
import org.minplug.database.data.PlayerData;

import java.util.concurrent.locks.ReentrantLock;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Database.class).asEagerSingleton();
        bind(ReentrantLock.class).asEagerSingleton();

        Multibinder<DAO> binder = Multibinder.newSetBinder(binder(), DAO.class);
        binder.addBinding().toInstance(new PlayerData());
    }
}
