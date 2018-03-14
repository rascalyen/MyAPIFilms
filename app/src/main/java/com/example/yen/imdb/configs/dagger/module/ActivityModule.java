package com.example.yen.imdb.configs.dagger.module;

import android.app.Activity;
import com.example.yen.imdb.configs.dagger.scope.PerActivity;
import com.example.yen.imdb.ui.BaseActivity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity
    Activity provideBaseActivity() {
        return activity;
    }

    @Provides @PerActivity
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}