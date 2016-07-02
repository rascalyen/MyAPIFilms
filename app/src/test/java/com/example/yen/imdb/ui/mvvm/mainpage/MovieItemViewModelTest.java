package com.example.yen.imdb.ui.mvvm.mainpage;

import android.content.Context;
import android.widget.ImageView;
import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.mvvm.detailpage.DetailActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class MovieItemViewModelTest extends RobolectricTestCase {

    @Mock Context context;
    @Mock Picasso picasso;
    @Mock Movie movie;
    @Mock ImageView view;
    @Mock RequestCreator picassoCreator;
    private String test;
    private String genre;
    private MovieItemViewModel movieItemViewModel;
    private List<String> genres = new ArrayList<>();


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        movieItemViewModel = new MovieItemViewModel(context, movie, picasso);
        createGenres();
    }

    private void createGenres() {
        genre = "Rock";
        genres.add(genre);
    }

    @Test public void getPosterUrl() {
        movieItemViewModel.getPosterUrl();

        verify(movie).getUrlPoster();
    }

    @Test public void loadPosterImage() {
        given(picasso.load("")).willReturn(picassoCreator);
        given(picasso.load("").fit()).willReturn(picassoCreator);

        movieItemViewModel.loadPosterImage(view, "");

        verify(picasso.load("").fit()).into(view);
    }

    @Test public void getTitle() {
        movieItemViewModel.getTitle();

        verify(movie).getTitle();
    }

    @Test public void getInfoAsNotRated() {
        given(movie.getGenres()).willReturn(genres);

        test = movieItemViewModel.getInfo();

        verify(movie).getRated();
        verify(movie).getRuntime();
        verify(movie).getGenres();
        assertThat(test, is("Not Rated | " + genre));
    }

    @Test public void getInfoAsRated() {
        given(movie.getGenres()).willReturn(genres);
        given(movie.getRated()).willReturn("PG-13");

        test = movieItemViewModel.getInfo();

        verify(movie, times(3)).getRated();
        verify(movie).getRuntime();
        verify(movie).getGenres();
        assertThat(test, is("PG-13 | " + genre));
    }

    @Test public void onItemClick() {
        movieItemViewModel.onItemClick(view);

        verify(context).startActivity(DetailActivity.getCalled(context, movie));
    }

    @After public void tearDown() {
        context = null;
        movie = null;
        picasso = null;
        view = null;
        picassoCreator = null;
        genre = null;
        movieItemViewModel = null;
        test = null;
        genres = null;
    }

}