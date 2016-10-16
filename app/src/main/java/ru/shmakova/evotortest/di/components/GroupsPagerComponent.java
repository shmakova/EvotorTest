package ru.shmakova.evotortest.di.components;

import android.support.annotation.NonNull;

import dagger.Subcomponent;
import ru.shmakova.evotortest.di.modules.GroupsPagerModule;
import ru.shmakova.evotortest.di.scopes.PerFragment;
import ru.shmakova.evotortest.presentation.presenters.GroupsPagerPresenter;
import ru.shmakova.evotortest.ui.fragments.GroupsPagerFragment;

/**
 * Created by shmakova on 16.10.16.
 */

@PerFragment
@Subcomponent(modules = GroupsPagerModule.class)
public interface GroupsPagerComponent {
    void inject(@NonNull GroupsPagerFragment groupsPagerFragment);

    GroupsPagerPresenter presenter();
}
