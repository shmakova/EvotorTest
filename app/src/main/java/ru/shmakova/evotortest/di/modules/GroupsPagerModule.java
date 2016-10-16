package ru.shmakova.evotortest.di.modules;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.shmakova.evotortest.data.groups.GroupsRepository;
import ru.shmakova.evotortest.data.groups.GroupsRepositoryImpl;
import ru.shmakova.evotortest.di.scopes.PerFragment;
import ru.shmakova.evotortest.presentation.presenters.GroupsPagerPresenter;

/**
 * Created by shmakova on 16.10.16.
 */


@Module
public class GroupsPagerModule {
    @Provides
    @NonNull
    public GroupsPagerPresenter provideGroupsPagerPresenter(@NonNull GroupsRepository groupsRepository) {
        return new GroupsPagerPresenter(groupsRepository);
    }

    @Provides
    @PerFragment
    @NonNull
    public GroupsRepository provideGroupsRepository() {
        return new GroupsRepositoryImpl();
    }

}
