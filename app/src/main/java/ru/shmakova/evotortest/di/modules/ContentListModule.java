package ru.shmakova.evotortest.di.modules;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.shmakova.evotortest.data.content.NixanService;
import ru.shmakova.evotortest.presentation.presenters.ContentListPresenter;

/**
 * Created by shmakova on 16.10.16.
 */


@Module
public class ContentListModule {
    @Provides
    @NonNull
    public ContentListPresenter provideSchoolsPresenter(@NonNull NixanService nixanService) {
        return new ContentListPresenter(nixanService);
    }
}
