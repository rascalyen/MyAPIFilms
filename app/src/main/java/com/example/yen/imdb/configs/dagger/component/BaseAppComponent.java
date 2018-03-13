package com.example.yen.imdb.configs.dagger.component;

import com.example.yen.imdb.IMDBApplication;
import com.example.yen.imdb.configs.dagger.module.ActivityModule;


interface BaseAppComponent {

    ActivityComponent plus(ActivityModule activityModule);

    void injectApplication(IMDBApplication application);

}