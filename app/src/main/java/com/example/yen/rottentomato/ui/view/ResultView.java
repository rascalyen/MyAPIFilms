package com.example.yen.rottentomato.ui.view;

import com.example.yen.rottentomato.data.model.Movie;

import java.util.List;

/**
 * Created by yenhuang on 2/10/16.
 */
public interface ResultView extends ProgressView {

    void viewListResult(List<Movie> movies);

}
