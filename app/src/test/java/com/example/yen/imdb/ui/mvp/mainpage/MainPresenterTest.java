package com.example.yen.imdb.ui.mvp.mainpage;

import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.data.entity.InTheaterEntity;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.response.Data;
import com.example.yen.imdb.data.response.Error;
import com.example.yen.imdb.data.response.IMDBResponse;
import com.example.yen.imdb.web.IMDBClient;
import com.example.yen.imdb.web.service.IMDBService;
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
    @Mock InTheaterEntity inTheaterEntity;
    @Mock MovieEntity movieEntity;
    @Captor ArgumentCaptor<Callback<IMDBResponse>> callbackCaptor;
    private IMDBClient imdbClient;
    private MainPresenter mainPresenter;
    private List<InTheaterEntity> theaters = new ArrayList<>();
    private List<MovieEntity> movies = new ArrayList<>();


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        imdbClient = new IMDBClient(imdbService);
        mainPresenter = new MainPresenter(imdbClient, properties);
        mainPresenter.attachViewMVP(mainView);
        createMockInTheater();
    }

    private void createMockInTheater() {
        movies.add(movieEntity);
        inTheaterEntity.setMovies(movies);
        theaters.add(inTheaterEntity);
        theaters.add(inTheaterEntity);
    }


    @Test public void initialize() {
        given(imdbClient.getImdbService().getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).willReturn(call);

        mainPresenter.initialize();

        verify(mainView).clearMovies();
        verify(mainView).hideRetry();
        verify(mainView).showProgress();
    }

    @Test public void getInTheaters() {
        given(properties.getProperty("token")).willReturn("string");
        given(imdbClient.getImdbService().getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).willReturn(call);

        mainPresenter.getInTheaters();

        verify(imdbClient.getImdbService()).getInTheaters(anyString(), anyString(), anyString());
        verify(imdbClient.getImdbService().getInTheaters(properties.getProperty("token"),
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
        given(imdbClient.getImdbService().getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)).willReturn(call);

        mainPresenter.initialize();
        mainPresenter.cancelCall();

        verify(call).cancel();
    }

    @After public void tearDown() {
        imdbClient = null;
        imdbService = null;
        properties = null;
        mainView = null;
        call = null;
        imdbResponse = null;
        data = null;
        error = null;
        inTheaterEntity = null;
        movieEntity = null;
        callbackCaptor = null;
        mainPresenter = null;
        theaters = null;
        movies = null;
    }

}