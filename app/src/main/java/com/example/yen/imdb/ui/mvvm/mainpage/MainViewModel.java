package com.example.yen.imdb.ui.mvvm.mainpage;

import android.databinding.ObservableInt;
import android.view.View;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.response.IMDBResponse;
import com.example.yen.imdb.ui.ViewModel;
import com.example.yen.imdb.utils.ModelMapper;
import com.example.yen.imdb.web.IMDBClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel implements ViewModel {

    public final ObservableInt progressVisible;
    public final ObservableInt retryVisible;
    private IMDBClient IMDBClient;
    private Properties properties;
    private Call<IMDBResponse> call;
    private MainListener mainListener;


    @Inject public MainViewModel(IMDBClient IMDBClient, Properties properties) {
        this.IMDBClient = IMDBClient;
        this.properties = properties;
        retryVisible = new ObservableInt(View.GONE);
        progressVisible = new ObservableInt(View.GONE);
    }


    public void setMainListener(MainListener mainListener) {
        this.mainListener = mainListener;
    }

    public void initialize() {
        mainListener.clearMovies();
        retryVisible.set(View.GONE);
        progressVisible.set(View.VISIBLE);
        getInTheaters();
    }

    protected void getInTheaters() {
        call = IMDBClient.getIMDBService().getInTheaters(properties.getProperty("token"));

        call.enqueue(new Callback<IMDBResponse>() {
            @Override
            public void onResponse(Call<IMDBResponse> call, Response<IMDBResponse> response) {

                if (response.isSuccessful() && response.body().getError() == null)
                    onSuccess(response.body());
                else
                    onError(response.body());
            }

            @Override
            public void onFailure(Call<IMDBResponse> call, Throwable t) {
                System.out.println("######  MOM  I failed.....");
            }
        });
    }

    protected void onSuccess(IMDBResponse imdbResponse) {
        List<MovieEntity> movieEntities = imdbResponse.getData().getInTheaters().get(1).getMovies();
        movieEntities.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
        if (mainListener != null) {
            mainListener.viewMovies(createMovieList(movieEntities));
            progressVisible.set(View.GONE);
        }
    }

    protected void onError(IMDBResponse imdbResponse) {
        retryVisible.set(View.VISIBLE);
        progressVisible.set(View.GONE);

        if (mainListener != null)
            mainListener.showMessage(imdbResponse.getError().getCode()
                    + " : " + imdbResponse.getError().getMessage());
    }

    private List<Movie> createMovieList(List<MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();

        for (MovieEntity entity : movieEntities)
            movies.add(ModelMapper.toMovieModel(entity));

        return movies;
    }

    @Override
    public void onDestroy() {
        if (call != null && !call.isCanceled())
            call.cancel();
    }


    public interface MainListener {
        void showMessage(String message);
        void clearMovies();
        void viewMovies(List<Movie> movies);
    }

}