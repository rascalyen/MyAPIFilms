package com.example.yen.imdb.ui.dependency.component;

import com.example.yen.imdb.ui.dependency.module.ActivityModule;
import com.example.yen.imdb.ui.dependency.scope.PerActivity;
import com.example.yen.imdb.ui.view.activity.BaseActivity;
import com.example.yen.imdb.ui.view.adapter.MovieAdapter;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void injectActivity(BaseActivity activity);

}
