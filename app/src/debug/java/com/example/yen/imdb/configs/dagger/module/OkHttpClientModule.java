package com.example.yen.imdb.configs.dagger.module;

import com.example.yen.imdb.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


@Module
public class OkHttpClientModule implements BaseHttpClientModule {

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(Cache cache,
                                     HttpLoggingInterceptor http, StethoInterceptor stetho) {

        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(BuildConfig.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(BuildConfig.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .addInterceptor(http)
                .addNetworkInterceptor(stetho)
                .build();
    }

}