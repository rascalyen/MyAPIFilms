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


public class MainPresenter implements Presenter<MainView> {

    private IMDBClient IMDBClient;
    private Properties properties;
    private MainView mainView;
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

                if (response.isSuccessful()) {
                    IMDBResponse imdbResponse = response.body();
                    List<MovieEntity> movies = imdbResponse.getData().getInTheaters().get(1).getMovies();
                    movies.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
                    if (mainView != null) {
                        mainView.viewMovies(movies);
                        mainView.hideProgress();
                    }
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

    public void cancelCall() {

        if (call != null && !call.isCanceled()) {
            call.cancel();
            System.out.println("Call got cancelled.");
        }
    }

    @Override
    public void attachViewMVP(@NonNull MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachViewMVP() {
        this.mainView = null;
    }

}