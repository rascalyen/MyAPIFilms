package com.example.yen.imdb.ui.dependency.component;

import com.example.yen.imdb.ui.dependency.module.ActivityModule;
import com.example.yen.imdb.ui.dependency.scope.PerActivity;
import com.example.yen.imdb.ui.view.adapter.MovieAdapter;
import com.example.yen.imdb.ui.view.fragment.MainFragment;
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
