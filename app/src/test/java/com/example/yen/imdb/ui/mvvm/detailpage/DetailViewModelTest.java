package com.example.yen.imdb.ui.mvvm.detailpage;

import android.widget.ImageView;
import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.model.Person;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class DetailViewModelTest extends RobolectricTestCase {

    @Mock Movie movie;
    @Mock Picasso picasso;
    @Mock ImageView view;
    @Mock RequestCreator picassoCreator;
    @Mock Person person;
    private String language;
    private DetailViewModel detailViewModel;
    private String test;
    private List<Person> directors = new ArrayList<>();
    private List<String> languages = new ArrayList<>();


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        detailViewModel = new DetailViewModel(picasso);
        detailViewModel.setMovie(movie);
        createList();
    }

    private void createList() {
        directors.add(person);
        language = "English";
        languages.add(language);
    }

    @Test public void getPosterUrl() {
        detailViewModel.getPosterUrl();

        verify(movie).getUrlPoster();
    }

    @Test public void loadPosterImage() {
        given(picasso.load("")).willReturn(picassoCreator);
        given(picasso.load("").fit()).willReturn(picassoCreator);

        detailViewModel.loadPosterImage(view, "");

        verify(picasso.load("").fit()).into(view);
    }

    @Test public void getInfoAsNotRated() {
        test = detailViewModel.getInfo();

        verify(movie).getRuntime();
        verify(movie).getRated();
        assertThat(test, is("Not Rated"));
    }

    @Test public void getInfoAsRated() {
        given(movie.getRated()).willReturn("PG-13");

        test = detailViewModel.getInfo();
        verify(movie).getRuntime();
        verify(movie, times(3)).getRated();
        assertThat(test, is("PG-13"));
    }

    @Test public void getGenre() {
        detailViewModel.getGenre();

        verify(movie).getGenres();
    }

    @Test public void getDirector() {
        given(movie.getDirectors()).willReturn(directors);

        detailViewModel.getDirector();

        verify(movie).getDirectors();
    }

    @Test public void getLanguage() {
        given(movie.getLanguages()).willReturn(languages);

        test = detailViewModel.getLanguage();

        verify(movie).getLanguages();
        assertEquals(test, language);
    }

    @Test public void getPlot() {
        detailViewModel.getPlot();

        verify(movie).getPlot();
    }

    @After public void tearDown() {
        movie = null;
        picasso = null;
        view = null;
        picassoCreator = null;
        person = null;
        language = null;
        detailViewModel = null;
        test = null;
        directors = null;
        languages = null;
    }

}