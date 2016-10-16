package ru.shmakova.evotortest.di.components;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import ru.shmakova.evotortest.di.modules.ApplicationModule;
import ru.shmakova.evotortest.di.modules.ContentListModule;
import ru.shmakova.evotortest.di.modules.GroupsPagerModule;
import ru.shmakova.evotortest.di.modules.NetworkModule;
import ru.shmakova.evotortest.ui.activities.MainActivity;

/**
 * Created by shmakova on 16.10.16.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {
    @NonNull
    ContentListComponent plus(ContentListModule module);

    @NonNull
    GroupsPagerComponent plus(GroupsPagerModule module);

    void inject(@NonNull MainActivity mainActivity);
}
