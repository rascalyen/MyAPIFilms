package com.example.yen.imdb.utils;

import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.data.entity.PersonEntity;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.data.model.Person;
import java.util.ArrayList;
import java.util.List;


public class ModelMapper {

    public static ArrayList<Movie> toMovieModel(List<MovieEntity> entityList) {

        ArrayList<Movie> movieList = new ArrayList<>();
        for(MovieEntity movieEntity : entityList) {
            Movie movie = new Movie();
            movie.setTitle(movieEntity.getTitle());
            movie.setRuntime(movieEntity.getRuntime());
            movie.setPlot(movieEntity.getPlot());
            movie.setRated(movieEntity.getRated());
            movie.setUrlPoster(movieEntity.getUrlPoster());

            List<String> languageList = new ArrayList<>();
            for (String language : movieEntity.getLanguages())
                languageList.add(language);
            movie.setLanguages(languageList);

            List<String> genreList = new ArrayList<>();
            for (String genre : movieEntity.getGenres())
                genreList.add(genre);
            movie.setGenres(genreList);

            List<Person> directorList = new ArrayList<>();
            for (PersonEntity personEntity : movieEntity.getDirectors())
                directorList.add(new Person(personEntity.getName()));
            movie.setDirectors(directorList);

            movieList.add(movie);
        }

        return movieList;
    }

}