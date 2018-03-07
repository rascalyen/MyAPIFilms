package com.example.yen.imdb.ui.mvp.mainpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yen.imdb.R;
import com.example.yen.imdb.configs.GlideApp;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.ui.navigation.Navigator;
import com.example.yen.imdb.utils.StringUtils;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Navigator navigator;
    private List<Movie> movies;


    @Inject public MovieAdapter(Navigator navigator) {
        this.navigator = navigator;
    }


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_movie, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        final Context context = holder.itemView.getContext();
        final Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        setInfoText(holder, movie);

        GlideApp.with(context).load(movie.getUrlPoster()).fitCenter().into(holder.movieImage);

        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToDetail(context, movie);
            }
        });

    }

    private void setInfoText(MovieHolder holder, Movie movie) {

        StringBuilder sb = new StringBuilder();
        sb.append((movie.getRated() == null || movie.getRated().isEmpty()) ?
                "Not Rated" : movie.getRated()).append(" | ")
                .append(StringUtils.makeMinToHour(movie.getRuntime()))
                .append(movie.getGenres().get(0));

        holder.info.setText(sb);
    }


    @Override
    public int getItemCount() {

        return movies == null ? 0 : movies.size();
    }

    public void addAll(List<Movie> movies) {
        if (!this.movies.isEmpty()) {
            this.movies.clear();
            this.movies.addAll(movies);
        }
        else
            this.movies.addAll(movies);

        notifyDataSetChanged();
    }

    public void clearAll() {
        if (!this.movies.isEmpty()) {
            this.movies.clear();
            notifyDataSetChanged();
        }
    }


    static class MovieHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_movie)
        ImageView movieImage;
        @BindView(R.id.text_title)
        TextView title;
        @BindView(R.id.text_info)
        TextView info;
        @BindView(R.id.text_view)
        TextView viewDetail;


        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}