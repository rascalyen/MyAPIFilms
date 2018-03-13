package com.example.yen.imdb.configs;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.imdb.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.inject.Inject;
import okhttp3.Cache;


public final class AppConfiguration {

    private Properties properties;
    private Cache cache;


    @Inject public AppConfiguration(@NonNull Application context) {
        this.properties = setProperties(context);
        this.cache = setCache(context);
    }


    private Cache setCache(@NonNull Application context) {

        return new Cache(new File(context.getCacheDir(),
                properties.getProperty("diskCachePath")),
                Integer.parseInt(properties.getProperty("diskCacheSizeMB")) * 1024 * 1024);
    }

    public Cache getCache() {
        return cache;
    }


    private Properties setProperties(@NonNull Application context) {

        InputStream rawResource = context.getResources().openRawResource(R.raw.imdb);
        Properties properties = new Properties();
        try {
            properties.load(rawResource);
        }
        catch (IOException e) {
            Log.e(AppConfiguration.class.getSimpleName(), "Can't load properties: " + e.toString());
        }

        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

}