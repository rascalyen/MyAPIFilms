package com.example.yen.imdb.ui.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.mvvm.detailpage.DetailActivity;
import javax.inject.Inject;


public final class Navigator {

    @Inject public Navigator() {}


    public void navigateToDetail(@NonNull Context context, Movie movie) {
        context.startActivity(DetailActivity.getCalled(context, movie));
    }

}