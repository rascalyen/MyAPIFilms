package com.example.yen.imdb.ui.mvp.mainpage;

import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.ProgressViewMVP;
import java.util.List;


public interface MainViewMVP extends ProgressViewMVP {

    void clearMovies();
    void viewMovies(List<Movie> movies);

}