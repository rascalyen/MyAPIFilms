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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@PerActivity
public class MainPresenter implements Presenter<MainViewMVP> {

    private IMDBService imdbService;
    private Properties properties;
    private CompositeDisposable compositeDisposable;
    private MainViewMVP mainView;


    @Inject public MainPresenter(IMDBService imdbService,
                                 Properties properties, CompositeDisposable compositeDisposable) {
        this.imdbService = imdbService;
        this.properties = properties;
        this.compositeDisposable = compositeDisposable;
    }


    public void initialize() {
        mainView.clearMovies();
        mainView.hideRetry();
        mainView.showProgress();
        getInTheaters();
    }

    protected void getInTheaters() {
        Disposable disposable = imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError, () -> mainView.hideProgress());

        compositeDisposable.add(disposable);
    }

    protected void onSuccess(IMDBResponse imdbResponse) {

        if (imdbResponse != null) {
            if (imdbResponse.getError() == null) {
                List<Movie> movies = imdbResponse.getData().getInTheaters().get(1).getMovies();
                movies.addAll(imdbResponse.getData().getInTheaters().get(0).getMovies());
                mainView.viewMovies(movies);
            }
            else {
                mainView.showRetry();
                mainView.showMessage(imdbResponse.getError().getCode()
                        + " : " + imdbResponse.getError().getMessage());
            }
        }

    }

    protected void onError(Throwable throwable) {
        Log.i(MainPresenter.class.getSimpleName(), "######  MOM  I failed.....");
    }

    public void cancelCall() {

        compositeDisposable.clear();
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