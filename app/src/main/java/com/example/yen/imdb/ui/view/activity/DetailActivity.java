package com.example.yen.imdb.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yen.imdb.R;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.ui.dependency.HasComponent;
import com.example.yen.imdb.ui.dependency.component.ActivityComponent;
import com.example.yen.imdb.ui.dependency.component.FragmentComponent;
import com.example.yen.imdb.utils.StringUtils;
import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Bind(R.id.toolbar)             Toolbar toolbar;
    @Bind(R.id.image_movie)         ImageView poster;
    @Bind(R.id.text_info)           TextView info;
    @Bind(R.id.text_genre)          TextView genre;
    @Bind(R.id.text_director)       TextView director;
    @Bind(R.id.text_lang)           TextView language;
    @Bind(R.id.text_plot)           TextView plot;

    private MovieEntity movie;


    public static Intent getCalled(Context context, MovieEntity movie) {
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
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    private void injectComponent() {
        activityComponent = FragmentComponent.Initializer
                .init(getApplicationComponent(), getActivityModule());
        activityComponent.injectActivity(this);
    }

    private void initializeActivity() {
        movie = (MovieEntity) getIntent().getSerializableExtra(EXTRA_MOVIE);

        setupActionBar();
        setupMovieDetail();
    }

    private void setupMovieDetail() {
        picasso.load(movie.getUrlPoster()).fit().into(poster);

        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.makeMinToHour(movie.getRuntime()));
        sb.append((movie.getRated() == null || movie.getRated().isEmpty()) ? "Not Rated" : movie.getRated());
        info.setText(sb);

        sb.delete(0, sb.length());
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (i == movie.getGenres().size()-1)
                sb.append(movie.getGenres().get(i));
            else
                sb.append(movie.getGenres().get(i) + ", ");
        }
        genre.setText(sb);

        director.setText(movie.getDirectors().get(0).getName());
        language.setText(movie.getLanguages().get(0));
        plot.setText(movie.getPlot());
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(movie.getTitle());
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}