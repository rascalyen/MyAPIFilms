package com.example.yen.rottentomato.ui.navigation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.yen.rottentomato.data.model.Movie;
import com.example.yen.rottentomato.ui.view.activity.DetailActivity;

import java.util.Properties;

import javax.inject.Inject;

/**
 * Created by yenhuang on 2/10/16.
 */
public class Navigator {

    private Properties properties;


    @Inject public Navigator(Properties properties) {
        this.properties = properties;
    }


    public void navigateToDetail(@NonNull Context context, Movie movie) {

        context.startActivity(DetailActivity.getCalled(context, movie));
    }

}