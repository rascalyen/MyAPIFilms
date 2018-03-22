package com.example.yen.imdb.ui.mvp.mainpage;

import android.util.Log;
import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.model.InTheater;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.repository.MovieRepository;
import com.example.yen.imdb.data.web.api.IMDBService;
import com.example.yen.imdb.data.web.response.Data;
import com.example.yen.imdb.data.web.response.IMDBResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;


public class MainPresenterTest extends RobolectricTestCase {

    @Mock MovieRepository movieRepo;
    @Mock IMDBService imdbService;
    @Mock Properties properties;
    @Mock MainViewMVP mainView;
    @Mock Observable<IMDBResponse> call;
    @Mock IMDBResponse imdbResponse;
    @Mock Data data;
    @Mock InTheater inTheater;
    @Mock Movie movie;
    @Mock Throwable throwable;
    private MainPresenter mainPresenter;
    private List<InTheater> theaters = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        compositeDisposable.add(new Disposable() {
            @Override
            public void dispose() {
                Log.d(MainPresenterTest.class.getSimpleName(), "test cancelCall");
            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        });

        mainPresenter = new MainPresenter(movieRepo, compositeDisposable);
        mainPresenter.attachViewMVP(mainView);
        createMockInTheater();
    }

    private void createMockInTheater() {
        movies.add(movie);
        inTheater.setMovies(movies);
        theaters.add(inTheater);
        theaters.add(inTheater);
    }


    @Test public void initialize() {
        // TODO - test with RxJAVA
    }

    @Test public void getInTheaters() {
        // TODO - test with RxJAVA
    }

    @Test public void onNextWithEmptyList() {

        mainPresenter.onNext(new ArrayList<Movie>());

        verify(mainView).showRetry();
    }

    @Test public void onNext() {

        mainPresenter.onNext(movies);

        verify(mainView).viewMovies(anyListOf(Movie.class));
    }

    @Test public void onError() {

        mainPresenter.onError(throwable);

        verify(mainView).showRetry();
        verify(mainView).showMessage(anyString());
    }

    @Test public void cancelCall() {

        mainPresenter.cancelCall();

        assertEquals(true, compositeDisposable.isDisposed());
    }


    @After public void tearDown() {
        movieRepo = null;
        imdbService = null;
        properties = null;
        mainView = null;
        call = null;
        imdbResponse = null;
        data = null;
        inTheater = null;
        movie = null;
        throwable = null;
        mainPresenter = null;
        theaters = null;
        movies = null;
        compositeDisposable = null;
    }

}