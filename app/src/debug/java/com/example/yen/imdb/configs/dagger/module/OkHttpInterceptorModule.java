package com.example.yen.imdb.configs.dagger.module;

import com.example.yen.imdb.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;


@Module
public class OkHttpInterceptorModule {

    @Provides @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(BuildConfig.HTTP_LOG_LEVEL);
    }

    @Provides @Singleton
    StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

}