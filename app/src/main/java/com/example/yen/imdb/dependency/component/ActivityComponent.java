package com.example.yen.imdb.dependency.component;

import com.example.yen.imdb.dependency.module.ActivityModule;
import com.example.yen.imdb.dependency.scope.PerActivity;
import com.example.yen.imdb.ui.BaseActivity;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void injectActivity(BaseActivity activity);

}
