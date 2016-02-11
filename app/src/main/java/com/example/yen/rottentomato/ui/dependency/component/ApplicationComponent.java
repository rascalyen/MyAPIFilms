package com.example.yen.rottentomato.ui.dependency.component;

import android.app.Application;

import com.example.yen.rottentomato.TomatoApplication;
import com.example.yen.rottentomato.ui.dependency.module.ApplicationModule;
import com.example.yen.rottentomato.web.TomatoClient;
import com.squareup.picasso.Picasso;

import java.util.Properties;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yenhuang on 2/10/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void injectApplication(TomatoApplication application);

    Properties properties();
    Picasso picasso();
    TomatoClient tomatoClient();


    final class Initializer {

        public static ApplicationComponent init(Application application) {

            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }
    }

}
