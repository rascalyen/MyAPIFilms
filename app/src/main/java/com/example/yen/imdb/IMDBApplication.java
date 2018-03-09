package com.example.yen.imdb;

import android.app.Application;
import android.os.Build;
import com.example.yen.imdb.dependency.component.ApplicationComponent;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;


public class IMDBApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();

        if (!isRoboUnitTest())
            Stetho.initializeWithDefaults(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
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

    public static boolean isRoboUnitTest() {
        return "robolectric".equals(Build.FINGERPRINT);
    }

}