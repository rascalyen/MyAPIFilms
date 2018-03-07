package com.example.yen.imdb.dependency.component;

import android.app.Application;
import com.example.yen.imdb.IMDBApplication;
import com.example.yen.imdb.dependency.module.ApplicationModule;
import com.example.yen.imdb.dependency.module.NetworkModule;
import com.example.yen.imdb.web.IMDBClient;
import java.util.Properties;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void injectApplication(IMDBApplication application);

    Properties properties();
    IMDBClient imdbClient();


    final class Initializer {

        public static ApplicationComponent init(Application application) {

            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .networkModule(new NetworkModule())
                    .build();
        }
    }

}
