package com.example.yen.imdb.ui;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.yen.imdb.IMDBApplication;
import com.example.yen.imdb.dependency.component.ActivityComponent;
import com.example.yen.imdb.dependency.component.ApplicationComponent;
import com.example.yen.imdb.dependency.module.ActivityModule;
import com.example.yen.imdb.ui.navigation.Navigator;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;


public class BaseActivity extends AppCompatActivity {

    @Inject Navigator navigator;
    @Inject Picasso picasso;
    public ActivityComponent activityComponent;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


/*  @Override protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

    public void addFragment(int containerViewId, Fragment fragment, String tag) {
        this.getSupportFragmentManager().beginTransaction().add(containerViewId, fragment, tag).commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment).addToBackStack(null).commit();
    }

    public void goToPreviousFragment() {
        this.getSupportFragmentManager().popBackStack();
    }

    public ApplicationComponent getApplicationComponent() {
        return ((IMDBApplication)getApplication()).getApplicationComponent();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public Picasso getPicasso() {
        return picasso;
    }

}