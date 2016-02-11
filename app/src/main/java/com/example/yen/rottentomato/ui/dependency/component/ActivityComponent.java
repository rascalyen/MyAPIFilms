package com.example.yen.rottentomato.ui.dependency.component;

import com.example.yen.rottentomato.ui.dependency.module.ActivityModule;
import com.example.yen.rottentomato.ui.dependency.scope.PerActivity;
import com.example.yen.rottentomato.ui.view.activity.BaseActivity;

import dagger.Component;

/**
 * Created by yenhuang on 2/10/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void injectActivity(BaseActivity activity);

}
