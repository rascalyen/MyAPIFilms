package com.example.yen.rottentomato;

import android.app.Application;

import com.example.yen.rottentomato.ui.dependency.component.ApplicationComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by yenhuang on 2/10/16.
 */
public class TomatoApplication extends Application {

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
