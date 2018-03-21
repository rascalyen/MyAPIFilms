package com.example.yen.imdb.ui.mvvm.detailpage;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yen.imdb.R;
import com.example.yen.imdb.configs.GlideApp;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.BaseActivity;
import com.example.yen.imdb.utils.StringUtils;
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

    DetailViewModel viewModel;


    public static Intent getCalled(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();

        initializeActivity();
    }

    private void preSetViews() {
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    private void initializeActivity() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.setMovieObservable(getIntent().getParcelableExtra(EXTRA_MOVIE));

        viewModel.getMovieObservable().observe(this, movie1 -> {
            setupActionBar(movie1);
            setupMovieDetail(movie1);
        });
    }

    private void setupMovieDetail(Movie movie) {
        GlideApp.with(this).load(movie.getUrlPoster()).fitCenter().into(poster);
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

    private void setupActionBar(Movie movie) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(movie.getTitle());
    }

}