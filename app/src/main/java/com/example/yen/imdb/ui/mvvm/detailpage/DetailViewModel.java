package com.example.yen.imdb.ui.mvvm.detailpage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.yen.imdb.data.model.Movie;


public class DetailViewModel extends ViewModel {

    private final MutableLiveData<Movie> movieObservable = new MutableLiveData<>();


    void setMovieObservable(Movie movie) {
        this.movieObservable.setValue(movie);
    }

    LiveData<Movie> getMovieObservable() {
        return movieObservable;
    }

}