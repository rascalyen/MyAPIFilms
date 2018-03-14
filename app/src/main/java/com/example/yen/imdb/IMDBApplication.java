package com.example.yen.imdb;

import android.app.Application;
import android.content.Context;
import com.example.yen.imdb.configs.DebugConfiguration;
import com.example.yen.imdb.configs.dagger.component.ApplicationComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


public class IMDBApplication extends Application {

    private ApplicationComponent applicationComponent;
    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();

        new DebugConfiguration().setup(this);
        setupLeakCanary();
        injectComponent();
    }


    private void setupLeakCanary() {

        if (LeakCanary.isInAnalyzerProcess(this))   return;

        refWatcher = LeakCanary.install(this);
    }

    private void injectComponent() {
        applicationComponent = ApplicationComponent.Initializer.init(this);
        applicationComponent.injectApplication(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ((IMDBApplication) context.getApplicationContext()).refWatcher;
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}