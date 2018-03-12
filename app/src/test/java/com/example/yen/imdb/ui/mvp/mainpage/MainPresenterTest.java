package com.example.yen.imdb.ui.mvp.mainpage;

import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.model.InTheater;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.web.response.Data;
import com.example.yen.imdb.data.web.response.Error;
import com.example.yen.imdb.data.web.response.IMDBResponse;
import com.example.yen.imdb.data.web.api.IMDBService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import retrofit2.Call;
import retrofit2.Callback;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class MainPresenterTest extends RobolectricTestCase {

    @Mock IMDBService imdbService;
    @Mock Properties properties;
    @Mock MainViewMVP mainView;
    @Mock Call<IMDBResponse> call;
    @Mock IMDBResponse imdbResponse;
    @Mock Data data;
    @Mock Error error;
    @Mock InTheater inTheater;
    @Mock Movie movie;
    @Captor ArgumentCaptor<Callback<IMDBResponse>> callbackCaptor;
    private MainPresenter mainPresenter;
    private List<InTheater> theaters = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        mainPresenter = new MainPresenter(imdbService, properties);
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
        given(imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).willReturn(call);

        mainPresenter.initialize();

        verify(mainView).clearMovies();
        verify(mainView).hideRetry();
        verify(mainView).showProgress();
    }

    @Test public void getInTheaters() {
        given(properties.getProperty("token")).willReturn("string");
        given(imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).willReturn(call);

        mainPresenter.getInTheaters();

        verify(imdbService).getInTheaters(anyString(), anyString(), anyString());
        verify(imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).enqueue(callbackCaptor.capture());
    }

    @Test public void onSuccess() {
        given(imdbResponse.getData()).willReturn(data);
        given(imdbResponse.getData().getInTheaters()).willReturn(theaters);

        mainPresenter.onSuccess(imdbResponse);

        verify(mainView).viewMovies(anyListOf(Movie.class));
        verify(mainView).hideProgress();
    }

    @Test public void onError() {
        given(imdbResponse.getError()).willReturn(error);

        mainPresenter.onError(imdbResponse);

        verify(mainView).showRetry();
        verify(mainView).hideProgress();
        verify(mainView).showMessage(anyString());
    }

    @Test public void cancelCall() {
        given(imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).willReturn(call);

        mainPresenter.initialize();
        mainPresenter.cancelCall();

        verify(call).cancel();
    }

    @After public void tearDown() {
        imdbService = null;
        properties = null;
        mainView = null;
        call = null;
        imdbResponse = null;
        data = null;
        error = null;
        inTheater = null;
        movie = null;
        callbackCaptor = null;
        mainPresenter = null;
        theaters = null;
        movies = null;
    }

}