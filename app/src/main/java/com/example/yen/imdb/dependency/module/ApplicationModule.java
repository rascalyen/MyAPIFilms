package com.example.yen.imdb.dependency.module;

import android.app.Application;
import com.example.yen.imdb.ui.navigation.Navigator;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Application provideApplication() {
        return application;
    }

    @Provides @Singleton Navigator provideNavigator() {
        return new Navigator();
    }

}
