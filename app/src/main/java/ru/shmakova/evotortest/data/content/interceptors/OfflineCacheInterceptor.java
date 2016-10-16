package ru.shmakova.evotortest.data.content.interceptors;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.shmakova.evotortest.utils.NetworkChecker;

/**
 * Created by shmakova on 21.09.16.
 */


public class OfflineCacheInterceptor implements Interceptor {
    private Context context;

    public OfflineCacheInterceptor(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkChecker.isNetworkAvailable(context)) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build();

            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .cacheControl(cacheControl)
                    .build();
        }

        return chain.proceed(request);
    }
}