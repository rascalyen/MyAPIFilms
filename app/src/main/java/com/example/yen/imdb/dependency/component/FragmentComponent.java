package com.example.yen.imdb.dependency.component;

import com.example.yen.imdb.dependency.module.ActivityModule;
import com.example.yen.imdb.dependency.scope.PerActivity;
import com.example.yen.imdb.ui.mvvm.mainpage.MovieAdapter;
import com.example.yen.imdb.ui.mvvm.mainpage.MainFragment;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface FragmentComponent extends ActivityComponent {

    void inject(MainFragment resultFragment);
    void inject(MovieAdapter movieAdapter);


    final class Initializer {

        public static FragmentComponent init(
                ApplicationComponent appComponent, ActivityModule activityModule) {

            return DaggerFragmentComponent.builder()
                    .applicationComponent(appComponent)
                    .activityModule(activityModule)
                    .build();
        }
    }

}
