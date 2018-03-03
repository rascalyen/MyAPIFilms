package com.example.yen.imdb.ui.mvp.detailpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yen.imdb.R;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.BaseActivity;
import com.example.yen.imdb.utils.StringUtils;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends BaseActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_movie)
    ImageView poster;
    @BindView(R.id.text_info)
    TextView info;
    @BindView(R.id.text_genre)
    TextView genre;
    @BindView(R.id.text_director)
    TextView director;
    @BindView(R.id.text_lang)
    TextView language;
    @BindView(R.id.text_plot)
    TextView plot;
    @Inject Picasso picasso;
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
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    private void injectComponent() {
        activityComponent.inject(this);
    }

    private void initializeActivity() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        setupActionBar();
        setupMovieDetail();
    }

    private void setupMovieDetail() {
        picasso.load(movie.getUrlPoster()).fit().into(poster);

        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.makeMinToHour(movie.getRuntime()))
                .append((movie.getRated() == null || movie.getRated().isEmpty()) ?
                        "Not Rated" : movie.getRated());
        info.setText(sb);

        sb.delete(0, sb.length());
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (i == movie.getGenres().size() - 1)
                sb.append(movie.getGenres().get(i));
            else
                sb.append(movie.getGenres().get(i)).append(", ");
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

}