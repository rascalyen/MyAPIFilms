package com.example.yen.imdb.configs;

import android.os.Build;
import com.example.yen.imdb.IMDBApplication;
import com.facebook.stetho.Stetho;


public final class DebugConfiguration implements BaseDebugConfig {

    @Override
    public void setup(IMDBApplication app) {

        initStetho(app);
    }


    private void initStetho(IMDBApplication app) {

        if (!isRoboUnitTest())
            Stetho.initializeWithDefaults(app);

    }

    private boolean isRoboUnitTest() {
        return "robolectric".equals(Build.FINGERPRINT);
    }

}