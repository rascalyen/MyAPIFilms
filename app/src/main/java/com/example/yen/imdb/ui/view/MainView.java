package com.example.yen.imdb.ui.view;

import com.example.yen.imdb.data.entity.MovieEntity;
import java.util.List;


public interface MainView extends ProgressView {

    void clearMovies();
    void viewMovies(List<MovieEntity> movies);

}