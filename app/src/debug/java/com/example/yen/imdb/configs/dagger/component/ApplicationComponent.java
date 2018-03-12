package com.example.yen.imdb.configs.dagger.component;

import android.app.Application;
import com.example.yen.imdb.configs.dagger.module.ApplicationModule;
import com.example.yen.imdb.configs.dagger.module.NetworkModule;
import com.example.yen.imdb.configs.dagger.module.OkHttpClientModule;
import com.example.yen.imdb.configs.dagger.module.OkHttpInterceptorModule;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = {
        ApplicationModule.class,
        OkHttpInterceptorModule.class,
        OkHttpClientModule.class,
        NetworkModule.class})
public interface ApplicationComponent extends BaseAppComponent {

    final class Initializer {

        public static ApplicationComponent init(Application application) {

            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .okHttpInterceptorModule(new OkHttpInterceptorModule())
                    .okHttpClientModule(new OkHttpClientModule())
                    .networkModule(new NetworkModule())
                    .build();
        }
    }

}