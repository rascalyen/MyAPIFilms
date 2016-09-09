package com.example.yen.imdb.ui.mvp.mainpage;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.response.IMDBResponse;
import com.example.yen.imdb.ui.Presenter;
import com.example.yen.imdb.utils.ModelMapper;
import com.example.yen.imdb.web.IMDBClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements Presenter<MainViewMVP> {

    private IMDBClient imdbClient;
    private Properties properties;
    private MainViewMVP mainView;
    private Call<IMDBResponse> call;


    @Inject public MainPresenter(IMDBClient imdbClient, Properties properties) {
        this.imdbClient = imdbClient;
        this.properties = properties;
    }


    public void initialize() {
        mainView.clearMovies();
        mainView.hideRetry();
        mainView.showProgress();
        getInTheaters();
    }

    protected void getInTheaters() {
        call = imdbClient.getImdbService().getInTheaters(properties.getProperty("token"));

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
                Log.i(MainPresenter.class.getSimpleName(), "######  MOM  I failed.....");
            }
        });
    }

    protected void onSuccess(IMDBResponse imdbResponse) {
        List<MovieEntity> movieEntities = imdbResponse.getData().getInTheaters().get(1).getMovies();
        movieEntities.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
        if (mainView != null) {
            mainView.viewMovies(createMovieList(movieEntities));
            mainView.hideProgress();
        }
    }

    protected void onError(IMDBResponse imdbResponse) {
        if (mainView != null) {
            mainView.showRetry();
            mainView.hideProgress();
            mainView.showMessage(imdbResponse.getError().getCode()
                    + " : " + imdbResponse.getError().getMessage());
        }
    }

    private List<Movie> createMovieList(List<MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();

        for (MovieEntity entity : movieEntities) {
            movies.add(ModelMapper.toMovieModel(entity));
        }

        return movies;
    }

    public void cancelCall() {

        if (call != null && !call.isCanceled())
            call.cancel();
    }

    @Override
    public void attachViewMVP(@NonNull MainViewMVP mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachViewMVP() {
        this.mainView = null;
    }

}