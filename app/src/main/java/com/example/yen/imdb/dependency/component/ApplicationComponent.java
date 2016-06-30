package com.example.yen.imdb.dependency.component;

import android.app.Application;
import android.databinding.DataBindingComponent;
import com.example.yen.imdb.IMDBApplication;
import com.example.yen.imdb.dependency.module.ApplicationModule;
import com.example.yen.imdb.web.IMDBClient;
import com.squareup.picasso.Picasso;
import java.util.Properties;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent extends DataBindingComponent {

    void injectApplication(IMDBApplication application);

    Properties properties();
    Picasso picasso();
    IMDBClient imdbClient();


    final class Initializer {

        public static ApplicationComponent init(Application application) {

            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }
    }

}
