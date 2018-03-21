package com.example.yen.imdb.ui.mvp.mainpage;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.imdb.configs.dagger.scope.PerActivity;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.repository.MovieRepository;
import com.example.yen.imdb.ui.Presenter;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


@PerActivity
public class MainPresenter implements Presenter<MainViewMVP> {

    private final MovieRepository movieRepo;
    private final CompositeDisposable compositeDisposable;
    private MainViewMVP mainView;


    @Inject MainPresenter(MovieRepository movieRepo,
                  CompositeDisposable compositeDisposable) {
        this.movieRepo = movieRepo;
        this.compositeDisposable = compositeDisposable;
    }


    void initialize() {
        mainView.clearMovies();
        mainView.hideRetry();
        mainView.showProgress();
        getInTheaters();
    }

    private void getInTheaters() {
        Disposable disposable = movieRepo.loadInTheaterMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError, () -> mainView.hideProgress());

        compositeDisposable.add(disposable);
    }

    void onNext(List<Movie> movies) {

        if (movies.isEmpty())
            mainView.showRetry();
        else
            mainView.viewMovies(movies);
    }

    void onError(Throwable throwable) {
        mainView.showRetry();
        mainView.showMessage("#####  MOM I failed  #####");
        Log.i(MainPresenter.class.getSimpleName(), throwable.getMessage());
    }

    void cancelCall() {
        compositeDisposable.dispose();
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