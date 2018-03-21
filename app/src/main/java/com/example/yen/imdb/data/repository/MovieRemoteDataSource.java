package com.example.yen.imdb.data.repository;

import com.example.yen.imdb.BuildConfig;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.web.api.IMDBService;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class MovieRemoteDataSource {

    private final IMDBService imdbService;
    private final Properties properties;


    @Inject
    public MovieRemoteDataSource(IMDBService imdbService, Properties properties) {
        this.imdbService = imdbService;
        this.properties = properties;
    }


    Observable<List<Movie>> getRemoteInTheaterMovies() {

        return imdbService.getInTheaters(properties.getProperty("token"),
                BuildConfig.FORMAT_JSON, BuildConfig.LANGUAGE)
                .flatMap(response ->
                {
                    List<Movie> movies = new ArrayList<>();

                    if (response != null && response.getError() == null) {
                        movies.addAll(response.getData().getInTheaters().get(1).getMovies());
                        movies.addAll(response.getData().getInTheaters().get(0).getMovies());
                    }
                    return Observable.just(movies);
                })
                .subscribeOn(Schedulers.io());
    }

}