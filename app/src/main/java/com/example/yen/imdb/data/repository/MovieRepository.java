package com.example.yen.imdb.data.repository;

import com.example.yen.imdb.data.model.Movie;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Observable;


@Singleton
public class MovieRepository {

    //TODO - local DB cache
    private final MovieRemoteDataSource remote;


    @Inject
    public MovieRepository(MovieRemoteDataSource remote) {
        this.remote = remote;
    }


    public Observable<List<Movie>> loadInTheaterMovies() {

        return remote.getRemoteInTheaterMovies();
    }

}