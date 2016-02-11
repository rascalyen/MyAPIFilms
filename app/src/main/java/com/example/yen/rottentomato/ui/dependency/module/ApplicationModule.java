package com.example.yen.rottentomato.ui.dependency.module;

import android.app.Application;

import com.example.yen.rottentomato.configs.Configuration;
import com.example.yen.rottentomato.web.TomatoClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.Properties;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yenhuang on 2/10/16.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Application provideApplication() {
        return application;
    }

    /*@Provides @Singleton Navigator provideNavigator(Properties properties) {
        return new Navigator(properties);
    }*/

    @Provides @Singleton Configuration provideConfiguration() {
        return new Configuration(application);
    }

    @Provides Properties provideProperties(Configuration config) {
        return config.getProperties();
    }

    @Provides OkHttpClient provideOkHttpClient(Configuration config) {
        return config.getOkHttpClient();
    }

    @Provides Picasso providePicasso(Configuration config) {
        return config.getPicasso();
    }

    @Provides @Singleton
    TomatoClient provideTomatoClient(OkHttpClient okHttpClient, Properties properties) {
        return new TomatoClient(okHttpClient, properties);
    }

}
