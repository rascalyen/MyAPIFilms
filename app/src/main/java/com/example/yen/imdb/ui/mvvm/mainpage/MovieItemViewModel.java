package com.example.yen.imdb.ui.mvvm.mainpage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.mvvm.detailpage.DetailActivity;
import com.example.yen.imdb.utils.StringUtils;
import com.squareup.picasso.Picasso;


public class MovieItemViewModel extends BaseObservable {

    private static Picasso picasso;
    private Context context;
    private Movie movie;


    public MovieItemViewModel(Context context, Movie movie, Picasso picasso) {
        this.context = context;
        this.movie = movie;
        this.picasso = picasso;
    }


    // Update this ViewModel when movie is changed
    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyChange();
    }

    public String getPosterUrl() {
        return movie.getUrlPoster();
    }

    @BindingAdapter({"posterUrl"})
    public static void loadPosterImage(ImageView view, String posterUrl) {
        picasso.load(posterUrl).fit().into(view);
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append((movie.getRated() == null || movie.getRated().isEmpty()) ? "Not Rated" : movie.getRated());
        sb.append(" | ");
        sb.append(StringUtils.makeMinToHour(movie.getRuntime()));
        sb.append(movie.getGenres().get(0));
        return sb.toString();
    }

    public void onItemClick(View view) {
        context.startActivity(DetailActivity.getCalled(context, movie));
    }

}