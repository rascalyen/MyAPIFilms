package com.example.yen.imdb.ui.mvvm.mainpage;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class MainViewModel extends ViewModel {

    private final MutableLiveData<Boolean> progressObservable = new MutableLiveData<>();
    private final MutableLiveData<Boolean> retryObservable = new MutableLiveData<>();
    private final MutableLiveData<String> errorMsgObservable = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesObservable = new MutableLiveData<>();

    @Inject MovieRepository movieRepo;
    @Inject CompositeDisposable compositeDisposable;


    public MainViewModel() {
        moviesObservable.setValue(new ArrayList<>());
    }


    void initialize() {
        retryObservable.setValue(false);
        progressObservable.setValue(true);
        getInTheaters();
    }

    private void getInTheaters() {
        Disposable disposable = movieRepo.loadInTheaterMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError, () -> progressObservable.setValue(false));

        compositeDisposable.add(disposable);
    }

    private void onNext(List<Movie> movies) {

        if (movies.isEmpty())
            retryObservable.setValue(true);
        else
            moviesObservable.setValue(movies);
    }

    private void onError(Throwable throwable) {
        retryObservable.setValue(true);
        errorMsgObservable.setValue("#####  MOM I failed  #####");
        Log.i(MainViewModel.class.getSimpleName(), throwable.getMessage());
    }

    void cancelCall() {
        compositeDisposable.dispose();
    }

    MutableLiveData<Boolean> getProgressObservable() {
        return progressObservable;
    }

    MutableLiveData<Boolean> getRetryObservable() {
        return retryObservable;
    }

    MutableLiveData<List<Movie>> getMoviesObservable() {
        return moviesObservable;
    }

    MutableLiveData<String> getErrorMsgObservable() {
        return errorMsgObservable;
    }

}