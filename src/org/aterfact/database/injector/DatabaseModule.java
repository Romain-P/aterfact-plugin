package org.aterfact.database.injector;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.aterfact.database.DAO;
import org.aterfact.database.Database;
import org.aterfact.database.data.PlayerData;

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
