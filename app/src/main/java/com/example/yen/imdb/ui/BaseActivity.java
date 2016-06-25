package com.example.yen.imdb.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.yen.imdb.IMDBApplication;
import com.example.yen.imdb.dependency.component.ActivityComponent;
import com.example.yen.imdb.dependency.component.ApplicationComponent;


public class BaseActivity extends AppCompatActivity {

    public ActivityComponent activityComponent;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = ActivityComponent.Initializer.init(getApplicationComponent(), this);
    }


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

}