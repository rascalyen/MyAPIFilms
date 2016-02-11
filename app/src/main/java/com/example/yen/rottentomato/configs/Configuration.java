package com.example.yen.rottentomato.configs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.yen.rottentomato.R;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by yenhuang on 2/10/16.
 */
public class Configuration {

    private Properties properties;
    private OkHttpClient okHttpClient;
    private Picasso picasso;


    @Inject public Configuration(@NonNull Context context) {
        this.properties = setProperties(context);
        this.okHttpClient = setOkHttpClient(context);
        this.picasso = setPicasso(context);
    }


    private Picasso setPicasso(@NonNull Context context) {

        Picasso picasso = new Picasso.Builder(context) //.indicatorsEnabled(true)
                .downloader(new OkHttpDownloader(okHttpClient))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e("com.interush.b88c", "Can't load image: " + uri);
                    }
                }).build();

        return picasso;
    }


    public Picasso getPicasso() {
        return picasso;
    }


    private OkHttpClient setOkHttpClient(@NonNull Context context) {


        OkHttpClient okClient = new OkHttpClient();

        okClient.setCache(new Cache(new File(context.getCacheDir(), properties.getProperty("diskCachePath")),
                Integer.parseInt(properties.getProperty("diskCacheSizeMB")) *1024 *1024));

        okClient.setConnectTimeout(
                Integer.parseInt(properties.getProperty("connectTimeoutSec")), TimeUnit.SECONDS);

        okClient.setReadTimeout(
                Integer.parseInt(properties.getProperty("readTimeoutSec")), TimeUnit.SECONDS);

        return okClient;
    }


    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private Properties setProperties(@NonNull Context context) {

        InputStream rawResource = context.getResources().openRawResource(R.raw.tomato);
        Properties properties = new Properties();
        try {
            properties.load(rawResource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

}