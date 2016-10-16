package ru.shmakova.evotortest.di.components;

import android.support.annotation.NonNull;

import dagger.Subcomponent;
import ru.shmakova.evotortest.di.modules.ContentListModule;
import ru.shmakova.evotortest.di.scopes.PerFragment;
import ru.shmakova.evotortest.presentation.presenters.ContentListPresenter;
import ru.shmakova.evotortest.ui.fragments.ContentListFragment;

/**
 * Created by shmakova on 16.10.16.
 */

@PerFragment
@Subcomponent(modules = ContentListModule.class)
public interface ContentListComponent {
    void inject(@NonNull ContentListFragment contentListFragment);

    ContentListPresenter presenter();
}
