package com.example.yen.imdb.ui.mvp.mainpage;

import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.ui.ProgressViewMVP;
import java.util.ArrayList;


public interface MainViewMVP extends ProgressViewMVP {

    void clearMovies();
    void viewMovies(ArrayList<MovieEntity> movies);

}