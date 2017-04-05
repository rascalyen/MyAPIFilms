package com.example.yen.imdb.dependency.module;

import android.app.Application;
import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.configs.Configuration;
import com.example.yen.imdb.web.IMDBClient;
import com.squareup.picasso.Picasso;
import java.util.Properties;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class NetworkModule {

    public NetworkModule() {}


    @Provides @Singleton
    Configuration provideConfiguration(Application application) {
        return new Configuration(application);
    }

    @Provides
    Properties provideProperties(Configuration config) {
        return config.getProperties();
    }

    @Provides
    OkHttpClient provideOkHttpClient(Configuration config) {
        return config.getOkHttpClient();
    }

    @Provides
    Picasso providePicasso(Configuration config) {
        return config.getPicasso();
    }

    @Provides @Named("IMDB_BASE_URL")
    String provideIMDBBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides @Singleton
    IMDBClient provideIMDBClient(OkHttpClient okHttpClient, @Named("IMDB_BASE_URL") String baseUrl) {
        return new IMDBClient(okHttpClient, baseUrl);
    }

}
