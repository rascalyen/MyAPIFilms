package com.example.yen.imdb.dependency.module;

import android.app.Application;
import com.example.yen.imdb.configs.Configuration;
import com.example.yen.imdb.web.IMDBClient;
import com.squareup.picasso.Picasso;
import java.util.Properties;
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

    @Provides @Singleton
    IMDBClient provideIMDBClient(OkHttpClient okHttpClient) {
        return new IMDBClient(okHttpClient);
    }

}
