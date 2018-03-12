package com.example.yen.imdb.ui.mvp.mainpage;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.configs.dagger.scope.PerActivity;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.web.response.IMDBResponse;
import com.example.yen.imdb.ui.Presenter;
import com.example.yen.imdb.data.web.api.IMDBService;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@PerActivity
public class MainPresenter implements Presenter<MainViewMVP> {

    private IMDBService imdbService;
    private Properties properties;
    private MainViewMVP mainView;
    private Call<IMDBResponse> call;


    @Inject public MainPresenter(IMDBService imdbService, Properties properties) {
        this.imdbService = imdbService;
        this.properties = properties;
    }


    public void initialize() {
        mainView.clearMovies();
        mainView.hideRetry();
        mainView.showProgress();
        getInTheaters();
    }

    protected void getInTheaters() {
        call = imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE);

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
        List<Movie> movies = imdbResponse.getData().getInTheaters().get(1).getMovies();
        movies.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
        if (mainView != null) {
            mainView.viewMovies(movies);
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