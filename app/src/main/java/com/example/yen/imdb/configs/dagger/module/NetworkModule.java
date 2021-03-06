package com.example.yen.imdb.configs.dagger.module;

import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.data.web.api.IMDBService;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;


@Module
public class NetworkModule {

    @Provides @Named("IMDB_BASE_URL")
    static String provideIMDBBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides @Singleton
    static Retrofit provideRetrofit(
            OkHttpClient okHttpClient, @Named("IMDB_BASE_URL") String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides @Singleton
    static IMDBService provideIMDBService(Retrofit retrofit) {
        return retrofit.create(IMDBService.class);
    }

}