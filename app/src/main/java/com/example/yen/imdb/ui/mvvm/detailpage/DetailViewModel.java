package com.example.yen.imdb.ui.mvvm.detailpage;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.utils.StringUtils;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;


public class DetailViewModel extends BaseObservable {

    private Movie movie;


    @Inject DetailViewModel() {}


    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyChange();
    }

    public String getPosterUrl() {
        return movie.getUrlPoster();
    }

    @BindingAdapter({"posterUrl"})
    public static void loadPosterImage(ImageView view, String posterUrl) {
        Picasso.with(view.getContext()).load(posterUrl).fit().into(view);
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.makeMinToHour(movie.getRuntime()));
        sb.append((movie.getRated() == null || movie.getRated().isEmpty()) ? "Not Rated" : movie.getRated());
        return sb.toString();
    }

    public String getGenre() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (i == movie.getGenres().size()-1)
                sb.append(movie.getGenres().get(i));
            else
                sb.append(movie.getGenres().get(i) + ", ");
        }
        return sb.toString();
    }

    public String getDirector() {
        return movie.getDirectors().get(0).getName();
    }

    public String getLanguage() {
        return movie.getLanguages().get(0);
    }

    public String getPlot() {
        return movie.getPlot();
    }

}