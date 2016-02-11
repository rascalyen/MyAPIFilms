package com.example.yen.rottentomato.ui.dependency.component;

import com.example.yen.rottentomato.ui.adapter.RecyclerAdapter;
import com.example.yen.rottentomato.ui.dependency.module.ActivityModule;
import com.example.yen.rottentomato.ui.dependency.scope.PerActivity;
import com.example.yen.rottentomato.ui.view.fragment.ResultFragment;

import dagger.Component;

/**
 * Created by yenhuang on 2/10/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface FragmentComponent extends ActivityComponent {

    void inject(ResultFragment resultFragment);
    void inject(RecyclerAdapter recyclerAdapter);


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
