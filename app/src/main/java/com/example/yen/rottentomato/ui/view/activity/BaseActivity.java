package com.example.yen.rottentomato.ui.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.yen.rottentomato.TomatoApplication;
import com.example.yen.rottentomato.ui.dependency.component.ActivityComponent;
import com.example.yen.rottentomato.ui.dependency.component.ApplicationComponent;
import com.example.yen.rottentomato.ui.dependency.module.ActivityModule;
import com.example.yen.rottentomato.ui.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by yenhuang on 2/10/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Inject Navigator navigator;
    ActivityComponent activityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

/*  @Override protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

    void addFragment(int containerViewId, Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).commit();
    }

    void replaceFragment(int containerViewId, Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment).addToBackStack(null).commit();
    }

    public void goToPreviousFragment() {
        this.getSupportFragmentManager().popBackStack();
    }

    ApplicationComponent getApplicationComponent() {
        return ((TomatoApplication)getApplication()).getApplicationComponent();
    }

    ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}