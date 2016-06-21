package com.example.yen.imdb.ui.mvp.mainpage;

import android.support.annotation.NonNull;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.response.IMDBResponse;
import com.example.yen.imdb.ui.Presenter;
import com.example.yen.imdb.utils.ModelMapper;
import com.example.yen.imdb.web.IMDBClient;
import java.util.ArrayList;
import java.util.Properties;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements Presenter<MainViewMVP> {

    private IMDBClient IMDBClient;
    private Properties properties;
    private MainViewMVP mainView;
    private Call<IMDBResponse> call;


    @Inject public MainPresenter(IMDBClient IMDBClient, Properties properties) {
        this.IMDBClient = IMDBClient;
        this.properties = properties;
    }


    public void initialize() {
        mainView.clearMovies();
        mainView.showProgress();
        getInTheaters();
    }

    private void getInTheaters() {
        call = IMDBClient.getIMDBService().getInTheaters(properties.getProperty("token"));

        call.enqueue(new Callback<IMDBResponse>() {
            @Override
            public void onResponse(Call<IMDBResponse> call, Response<IMDBResponse> response) {

                if (response.isSuccessful() && response.body().getError() == null) {
                    IMDBResponse imdbResponse = response.body();
                    ArrayList<MovieEntity> movieEntities = imdbResponse.getData().getInTheaters().get(1).getMovies();
                    movieEntities.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
                    if (mainView != null) {
                        mainView.viewMovies(ModelMapper.toMovieModel(movieEntities));
                        mainView.hideProgress();
                    }
                }
                else {
                    mainView.showMessage(response.body().getError().getCode()
                            + " : " + response.body().getError().getMessage());
                    mainView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<IMDBResponse> call, Throwable t) {
                System.out.println("######  MOM  I failed.....");
            }
        });
    }

    public void cancelCall() {

        if (call != null && !call.isCanceled()) {
            call.cancel();
            System.out.println("Call got cancelled.");
        }
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