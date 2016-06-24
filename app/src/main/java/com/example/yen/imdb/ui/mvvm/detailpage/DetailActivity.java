package com.example.yen.imdb.ui.mvvm.detailpage;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.yen.imdb.R;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.databinding.ActivityDetailBinding;
import com.example.yen.imdb.dependency.component.FragmentComponent;
import com.example.yen.imdb.ui.BaseActivity;


public class DetailActivity extends BaseActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;
    private Movie movie;


    public static Intent getCalled(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        injectComponent();
        initializeActivity();
    }

    private void preSetViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
    }

    private void injectComponent() {
        activityComponent = FragmentComponent.Initializer
                .init(getApplicationComponent(), getActivityModule());
        activityComponent.injectActivity(this);
    }

    private void initializeActivity() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setupActionBar();

        detailViewModel = new DetailViewModel(this, movie);
        binding.setViewModel(detailViewModel);
    }

    private void setupActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(movie.getTitle());
    }

}