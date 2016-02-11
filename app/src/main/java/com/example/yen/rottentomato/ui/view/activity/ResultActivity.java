package com.example.yen.rottentomato.ui.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.yen.rottentomato.R;
import com.example.yen.rottentomato.data.model.Movie;
import com.example.yen.rottentomato.ui.dependency.HasComponent;
import com.example.yen.rottentomato.ui.dependency.component.ActivityComponent;
import com.example.yen.rottentomato.ui.dependency.component.FragmentComponent;
import com.example.yen.rottentomato.ui.view.fragment.ResultFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultActivity extends BaseActivity implements HasComponent<ActivityComponent>,
        ResultFragment.ResultListener {

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
        handleIntent(getIntent(), savedInstanceState);
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


    private void handleIntent(Intent intent, Bundle savedInstanceState) {

        String query = "";
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
            query = intent.getStringExtra(SearchManager.QUERY);

        if (savedInstanceState == null)
            addFragment(R.id.fl_base, ResultFragment.newInstance(query));
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

    @Override
    public void moveToDetail(Movie movie) {
        navigator.navigateToDetail(this, movie);
    }

}