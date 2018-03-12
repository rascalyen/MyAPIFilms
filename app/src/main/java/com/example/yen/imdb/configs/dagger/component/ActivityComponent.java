package com.example.yen.imdb.configs.dagger.component;

import com.example.yen.imdb.configs.dagger.module.ActivityModule;
import com.example.yen.imdb.configs.dagger.scope.PerActivity;
import com.example.yen.imdb.ui.BaseActivity;
import com.example.yen.imdb.ui.mvp.detailpage.DetailActivity;
import com.example.yen.imdb.ui.mvp.mainpage.MainFragment;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainFragment resultFragment);
    void inject(DetailActivity activity);


    final class Initializer {

        public static ActivityComponent init(
                ApplicationComponent appComponent, BaseActivity activity) {

            return DaggerActivityComponent.builder()
                    .applicationComponent(appComponent)
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

}