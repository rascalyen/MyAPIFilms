package com.example.yen.imdb.ui.mvp.mainpage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.yen.imdb.R;
import com.example.yen.imdb.dependency.HasComponent;
import com.example.yen.imdb.dependency.component.ActivityComponent;
import com.example.yen.imdb.dependency.component.FragmentComponent;
import com.example.yen.imdb.ui.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    @Bind(R.id.toolbar)     Toolbar toolbar;
    private final String MAIN_TAG = "MAIN";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        injectComponent();
        initializeActivity();
    }

    private void preSetViews() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void injectComponent() {
        activityComponent = FragmentComponent.Initializer
                .init(getApplicationComponent(), getActivityModule());
        activityComponent.injectActivity(this);
    }

    private void initializeActivity() {
        setSupportActionBar(toolbar);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
        if (mainFragment == null)
            addFragment(R.id.fl_base, MainFragment.newInstance(), MAIN_TAG);
        else
            replaceFragment(R.id.fl_base, mainFragment);
    }

    @OnClick(R.id.text_refresh)
    public void onRefresh() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
        if (mainFragment != null)
            mainFragment.getMainPresenter().initialize();
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}