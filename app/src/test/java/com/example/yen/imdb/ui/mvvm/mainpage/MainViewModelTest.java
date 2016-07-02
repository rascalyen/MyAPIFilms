package com.example.yen.imdb.ui.mvvm.mainpage;

import android.view.View;
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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class MainViewModelTest extends RobolectricTestCase {

    @Mock IMDBService imdbService;
    @Mock Properties properties;
    @Mock MainViewModel.MainListener mainListener;
    @Mock Call<IMDBResponse> call;
    @Mock IMDBResponse imdbResponse;
    @Mock Data data;
    @Mock Error error;
    @Mock InTheaterEntity inTheaterEntity;
    @Mock MovieEntity movieEntity;
    @Captor ArgumentCaptor<Callback<IMDBResponse>> callbackCaptor;
    private IMDBClient imdbClient;
    private MainViewModel mainViewModel;
    private List<InTheaterEntity> theaters = new ArrayList<>();
    private List<MovieEntity> movies = new ArrayList<>();


    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        imdbClient = new IMDBClient(imdbService);
        mainViewModel = new MainViewModel(imdbClient, properties);
        mainViewModel.setMainListener(mainListener);
        createMockInTheater();
    }

    private void createMockInTheater() {
        movies.add(movieEntity);
        inTheaterEntity.setMovies(movies);
        theaters.add(inTheaterEntity);
        theaters.add(inTheaterEntity);
    }

    @Test public void defaultVisibilities() {
        assertThat(mainViewModel.progressVisible.get(), is(View.GONE));
        assertThat(mainViewModel.retryVisible.get(), is(View.GONE));
    }

    @Test public void initialize() {
        given(imdbClient.getIMDBService().getInTheaters(properties.getProperty("token"))).willReturn(call);

        mainViewModel.initialize();

        verify(mainListener).clearMovies();
        assertThat(mainViewModel.retryVisible.get(), is(View.GONE));
        assertThat(mainViewModel.progressVisible.get(), is(View.VISIBLE));
    }

    @Test public void getInTheaters() {
        given(properties.getProperty("token")).willReturn("string");
        given(imdbClient.getIMDBService().getInTheaters(properties.getProperty("token"))).willReturn(call);

        mainViewModel.getInTheaters();

        verify(imdbClient.getIMDBService()).getInTheaters(anyString());
        verify(imdbClient.getIMDBService().getInTheaters(properties.getProperty("token"))).enqueue(callbackCaptor.capture());
    }

    @Test public void onSuccess() {
        given(imdbResponse.getData()).willReturn(data);
        given(imdbResponse.getData().getInTheaters()).willReturn(theaters);

        mainViewModel.onSuccess(imdbResponse);

        verify(mainListener).viewMovies(anyListOf(Movie.class));
        assertThat(mainViewModel.progressVisible.get(), is(View.GONE));
    }

    @Test public void onError() {
        given(imdbResponse.getError()).willReturn(error);

        mainViewModel.onError(imdbResponse);

        assertThat(mainViewModel.retryVisible.get(), is(View.VISIBLE));
        assertThat(mainViewModel.progressVisible.get(), is(View.GONE));
        verify(mainListener).showMessage(anyString());
    }

    @Test public void onDestroy() {
        given(imdbClient.getIMDBService().getInTheaters(properties.getProperty("token"))).willReturn(call);

        mainViewModel.initialize();
        mainViewModel.onDestroy();

        verify(call).cancel();
    }

    @After public void tearDown() {
        imdbClient = null;
        imdbService = null;
        properties = null;
        mainListener = null;
        call = null;
        imdbResponse = null;
        data = null;
        error = null;
        inTheaterEntity = null;
        movieEntity = null;
        callbackCaptor = null;
        mainViewModel = null;
        theaters = null;
        movies = null;
    }

}