package com.example.yen.imdb.configs.dagger.component;

import com.example.yen.imdb.configs.dagger.module.ActivityModule;
import com.example.yen.imdb.configs.dagger.scope.PerActivity;
import com.example.yen.imdb.ui.BaseActivity;
import com.example.yen.imdb.ui.mvvm.mainpage.MainFragment;
import com.example.yen.imdb.ui.mvvm.mainpage.MainViewModel;
import dagger.Subcomponent;


@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainFragment resultFragment);
    void inject(MainViewModel mainViewModel);


    final class Initializer {

        public static ActivityComponent init(
                ApplicationComponent appComponent, BaseActivity activity) {

            return appComponent.plus(new ActivityModule(activity));
        }
    }

}