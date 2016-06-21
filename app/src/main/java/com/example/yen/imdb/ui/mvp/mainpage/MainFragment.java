package com.example.yen.imdb.ui.mvp.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.yen.imdb.R;
import com.example.yen.imdb.data.model.Movie;
import com.example.yen.imdb.dependency.component.FragmentComponent;
import com.example.yen.imdb.ui.BaseFragment;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainFragment extends BaseFragment implements MainViewMVP {

    @Bind(R.id.rl_progress)     RelativeLayout progressView;
    @Bind(R.id.rl_retry)        RelativeLayout noResultView;
    @Bind(R.id.recycler)        RecyclerView recyclerView;
    @Inject MainPresenter mainPresenter;
    private MovieAdapter movieAdapter;
    private static String MOVIES_STATE = "MOVIES_STATE";
    private ArrayList<Movie> movies;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        super();
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //mMainListener = (MainListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MainListener");
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        return view;
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

        setRecyclerView();
        initialize();
    }

    private void setRecyclerView() {
        movieAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void initialize() {
        this.getComponent(FragmentComponent.class).inject(this);
        this.getComponent(FragmentComponent.class).inject(movieAdapter);
        mainPresenter.attachViewMVP(this);
        if (movies.isEmpty())
            mainPresenter.initialize();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mainPresenter.cancelCall();
    }

    @Override public void onDestroy() {
        mainPresenter.detachViewMVP();
        super.onDestroy();
    }


    @Override
    public void showProgress() {
        if (progressView != null) {
            progressView.setVisibility(View.VISIBLE);
            this.getActivity().setProgressBarIndeterminateVisibility(true);
        }
    }

    @Override
    public void hideProgress() {
        if (progressView != null) {
            progressView.setVisibility(View.GONE);
            this.getActivity().setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override
    public void showRetry() {
        noResultView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        noResultView.setVisibility(View.GONE);
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
    public void viewMovies(ArrayList<Movie> movies) {
        if (movieAdapter != null) {
            movieAdapter.addAll(movies);
            this.movies = movies;
        }
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

}