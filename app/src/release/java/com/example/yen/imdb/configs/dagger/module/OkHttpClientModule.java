package com.example.yen.imdb.configs.dagger.module;

import com.example.yen.imdb.BuildConfig;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


@Module
public class OkHttpClientModule implements BaseHttpClientModule {

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {

        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(BuildConfig.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(BuildConfig.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .build();
    }

}