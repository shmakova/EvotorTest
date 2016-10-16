package ru.shmakova.evotortest.di.modules;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.shmakova.evotortest.data.content.AutoValueGsonTypeAdapterFactory;
import ru.shmakova.evotortest.data.content.NixanService;
import ru.shmakova.evotortest.data.content.interceptors.CacheInterceptor;
import ru.shmakova.evotortest.data.content.interceptors.OfflineCacheInterceptor;

/**
 * Created by shmakova on 16.10.16.
 */

@Module
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(
            Cache cache,
            HttpLoggingInterceptor loggingInterceptor,
            CacheInterceptor cacheInterceptor,
            OfflineCacheInterceptor offlineCacheInterceptor
    ) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addNetworkInterceptor(offlineCacheInterceptor)
                .cache(cache);
        return httpClient.build();
    }

    @Provides
    @Singleton
    OfflineCacheInterceptor provideOfflineCacheInterceptor(Application application) {
        return new OfflineCacheInterceptor(application);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NixanService.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    NixanService providesNixanService(Retrofit retrofit) {
        return retrofit.create(NixanService.class);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    CacheInterceptor provideCacheInterceptor() {
        return new CacheInterceptor();
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                .create();
    }
}
