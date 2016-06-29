package com.example.yen.imdb.ui.mvvm.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.databinding.ItemMovieBinding;
import java.util.List;
import javax.inject.Inject;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> movies;


    @Inject public MovieAdapter() {}


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MovieHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bindMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {

        return movies == null ? 0 : movies.size();
    }

    public void addAll(List<Movie> movies) {
        if ( !this.movies.isEmpty()) {
            this.movies.clear();
            this.movies.addAll(movies);
        }
        else {
            this.movies.addAll(movies);
        }
        notifyDataSetChanged();
    }

    public void clearAll() {
        if (!this.movies.isEmpty()) {
            this.movies.clear();
            notifyDataSetChanged();
        }
    }


    static class MovieHolder extends RecyclerView.ViewHolder {

        final ItemMovieBinding binding;


        MovieHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static MovieHolder create(LayoutInflater inflater, ViewGroup parent) {
            return new MovieHolder(ItemMovieBinding.inflate(inflater, parent, false));
        }

        void bindMovie(Movie movie) {
            if (binding.getViewModel() == null)
                binding.setViewModel(new MovieItemViewModel(itemView.getContext(), movie));
            else
                binding.getViewModel().setMovie(movie);

            binding.executePendingBindings();
        }

    }

}