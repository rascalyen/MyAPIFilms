package com.example.yen.imdb.configs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class Configuration {

    private Properties properties;
    private OkHttpClient okHttpClient;


    @Inject public Configuration(@NonNull Context context) {
        this.properties = setProperties(context);
        this.okHttpClient = setOkHttpClient(context);
    }


    @SuppressFBWarnings("ICAST_INTEGER_MULTIPLY_CAST_TO_LONG")
    private OkHttpClient setOkHttpClient(@NonNull Context context) {

        return new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getCacheDir(),
                        properties.getProperty("diskCachePath")),
                        Integer.parseInt(properties.getProperty("diskCacheSizeMB")) * 1024 * 1024))
                .connectTimeout(BuildConfig.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(BuildConfig.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


    private Properties setProperties(@NonNull Context context) {

        InputStream rawResource = context.getResources().openRawResource(R.raw.imdb);
        Properties properties = new Properties();
        try {
            properties.load(rawResource);
        }
        catch (IOException e) {
            Log.e(Configuration.class.getSimpleName(), "Can't load properties: " + e.toString());
        }

        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

}