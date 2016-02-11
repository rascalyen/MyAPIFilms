package com.example.yen.rottentomato.ui.dependency.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yenhuang on 2/10/16.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides Activity provideActivity() {
        return this.activity;
    }

}