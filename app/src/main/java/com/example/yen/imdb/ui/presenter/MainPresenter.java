package com.example.yen.imdb.ui.presenter;

import android.support.annotation.NonNull;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.response.IMDBResponse;
import com.example.yen.imdb.ui.view.MainView;
import com.example.yen.imdb.web.IMDBClient;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements Presenter {

    private IMDBClient IMDBClient;
    private Properties properties;
    private MainView mainView;


    @Inject public MainPresenter(IMDBClient IMDBClient, Properties properties) {
        this.IMDBClient = IMDBClient;
        this.properties = properties;
    }

    public void setMainView(@NonNull MainView mainView) {
        this.mainView = mainView;
    }

    public void initialize() {
        mainView.clearMovies();
        mainView.showProgress();
        getInTheaters();
    }

    private void getInTheaters() {
        Call<IMDBResponse> call = IMDBClient.getIMDBService().getInTheaters(properties.getProperty("token"));

        call.enqueue(new Callback<IMDBResponse>() {
            @Override
            public void onResponse(Call<IMDBResponse> call, Response<IMDBResponse> response) {

                if (response.isSuccessful()) {
                    IMDBResponse imdbResponse = response.body();
                    List<MovieEntity> movies = imdbResponse.getData().getInTheaters().get(1).getMovies();
                    movies.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
                    mainView.viewMovies(movies);
                    mainView.hideProgress();
                }
                else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<IMDBResponse> call, Throwable t) {
                System.out.println("######  MOM  I failed.....");
            }
        });
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}


    public void viewMovies(List<MovieEntity> movieEntities) {
        mainView.viewMovies(movieEntities);
    }

}