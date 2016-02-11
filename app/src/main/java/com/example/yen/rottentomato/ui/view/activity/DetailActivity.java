package com.example.yen.rottentomato.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.example.yen.rottentomato.R;
import com.example.yen.rottentomato.data.model.Movie;
import com.example.yen.rottentomato.ui.dependency.HasComponent;
import com.example.yen.rottentomato.ui.dependency.component.ActivityComponent;
import com.example.yen.rottentomato.ui.dependency.component.FragmentComponent;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yenhuang on 2/11/16.
 */
public class DetailActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    private static final String INTENT_PARAM_MOVIE            = "INTENT_PARAM_MOVIE";
    private static final String INSTANCESTATE_PARAM_MOVIE     = "INSTANCESTATE_PARAM_MOVIE";

    @Bind(R.id.toolbar)         Toolbar toolbar;
    @Bind(R.id.tv_title)        TextView title;
    @Bind(R.id.tv_rating)       TextView rating;
    @Bind(R.id.tv_description)  TextView des;
    private Movie movie;


    public static Intent getCalled(@NonNull Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(INTENT_PARAM_MOVIE, movie);
        return intent;
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        injectComponent();
        initializeActivity(savedInstanceState);
    }

    private void preSetViews() {
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    private void injectComponent() {
        activityComponent = FragmentComponent.Initializer
                .init(getApplicationComponent(), getActivityModule());
        activityComponent.injectActivity(this);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            movie = (Movie) this.getIntent().getSerializableExtra(INTENT_PARAM_MOVIE);
        } else {
            movie = (Movie) savedInstanceState.getSerializable(INSTANCESTATE_PARAM_MOVIE);
        }

        setUI();
    }

    private void setUI() {
        title.setText(movie.getTitle());
        rating.setText(movie.getMpaaRating());
        des.setText(movie.getSynopsis());
    }

    @Override protected void onSaveInstanceState(Bundle outState) {

        if (outState != null) {
            outState.putSerializable(INSTANCESTATE_PARAM_MOVIE, this.movie);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}
