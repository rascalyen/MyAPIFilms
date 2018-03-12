package com.example.yen.imdb.configs.dagger.module;

import android.app.Application;
import com.example.yen.imdb.configs.AppConfiguration;
import java.util.Properties;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;


@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    Properties provideProperties(AppConfiguration appConfiguration) {
        return appConfiguration.getProperties();
    }

    @Provides
    Cache provideCache(AppConfiguration appConfiguration) {
        return appConfiguration.getCache();
    }

}