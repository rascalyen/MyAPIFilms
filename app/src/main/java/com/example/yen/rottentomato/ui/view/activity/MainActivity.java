package com.example.yen.rottentomato.ui.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import com.example.yen.rottentomato.R;
import com.example.yen.rottentomato.ui.dependency.HasComponent;
import com.example.yen.rottentomato.ui.dependency.component.ActivityComponent;
import com.example.yen.rottentomato.ui.dependency.component.FragmentComponent;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    @Bind(R.id.toolbar)     Toolbar toolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        injectComponent();
        initializeActivity(savedInstanceState);
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

    private void initializeActivity(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}
