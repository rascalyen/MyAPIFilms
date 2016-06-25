package com.example.yen.imdb.dependency.component;

import android.app.Activity;
import com.example.yen.imdb.dependency.module.ActivityModule;
import com.example.yen.imdb.dependency.scope.PerActivity;
import com.example.yen.imdb.ui.mvvm.detailpage.DetailActivity;
import com.example.yen.imdb.ui.mvvm.mainpage.MainFragment;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainFragment resultFragment);
    void inject(DetailActivity activity);


    final class Initializer {

        public static ActivityComponent init(
                ApplicationComponent appComponent, Activity activity) {

            return DaggerActivityComponent.builder()
                    .applicationComponent(appComponent)
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

}