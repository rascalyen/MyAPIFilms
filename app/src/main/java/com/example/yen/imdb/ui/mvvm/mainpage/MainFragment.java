package com.example.yen.imdb.ui.mvvm.mainpage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yen.imdb.R;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.databinding.FragmentMoviesBinding;
import com.example.yen.imdb.dependency.component.ActivityComponent;
import com.example.yen.imdb.ui.BaseFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


public class MainFragment extends BaseFragment implements MainViewModel.MainListener {

    private static String MOVIES_STATE = "MOVIES_STATE";
    private FragmentMoviesBinding binding;
    private ArrayList<Movie> movies;
    @Inject MainViewModel mainViewModel;
    @Inject MovieAdapter movieAdapter;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        super();
    }


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIES_STATE, movies);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null)
            movies = savedInstanceState.getParcelableArrayList(MOVIES_STATE);
        else if (movies == null)
            movies = new ArrayList<>();

        injectComponent();
        setRecyclerView();
        initialize();
    }

    private void injectComponent() {
        this.getComponent(ActivityComponent.class).inject(this);
        mainViewModel.setMainListener(this);
        binding.setViewModel(mainViewModel);
    }

    private void setRecyclerView() {
        movieAdapter.setMovies(movies);
        binding.recycler.setAdapter(movieAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void initialize() {
        if (movies.isEmpty())
            mainViewModel.initialize();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        mainViewModel.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        this.showToastMessage(message);
    }

    @Override
    public void clearMovies() {
        if (movieAdapter != null) {
            movieAdapter.clearAll();
            movies.clear();
        }
    }

    @Override
    public void viewMovies(List<Movie> movies) {
        if (movieAdapter != null) {
            movieAdapter.addAll(movies);
            this.movies = (ArrayList<Movie>) movies;
        }
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

}