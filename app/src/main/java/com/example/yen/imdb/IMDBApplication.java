package com.example.yen.imdb;

import android.app.Application;
import com.example.yen.imdb.dependency.component.ApplicationComponent;
import com.squareup.leakcanary.LeakCanary;


public class IMDBApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        injectComponent();
    }

    private void injectComponent() {
        applicationComponent = ApplicationComponent.Initializer.init(this);
        applicationComponent.injectApplication(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}