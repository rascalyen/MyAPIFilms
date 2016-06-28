package com.example.yen.imdb.ui.mvvm.mainpage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.example.yen.imdb.R;
import com.example.yen.imdb.databinding.ActivityMainBinding;
import com.example.yen.imdb.dependency.HasComponent;
import com.example.yen.imdb.dependency.component.ActivityComponent;
import com.example.yen.imdb.ui.BaseActivity;


public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    private final String MAIN_TAG = "MAIN";
    private ActivityMainBinding binding;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        initializeActivity();
    }

    private void preSetViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        // You can make this as MenuItem, either way.
        binding.textRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    private void initializeActivity() {

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
        if (mainFragment == null)
            addFragment(R.id.fl_base, MainFragment.newInstance(), MAIN_TAG);
        else
            replaceFragment(R.id.fl_base, mainFragment);
    }

    public void onRefresh() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
        if (mainFragment != null && mainFragment.isVisible())
            mainFragment.getMainViewModel().initialize();
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}