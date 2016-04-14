package com.example.yen.imdb.ui.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.yen.imdb.R;
import com.example.yen.imdb.data.entity.MovieEntity;
import com.example.yen.imdb.ui.dependency.component.FragmentComponent;
import com.example.yen.imdb.ui.presenter.MainPresenter;
import com.example.yen.imdb.ui.view.MainView;
import com.example.yen.imdb.ui.view.adapter.MovieAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainFragment extends BaseFragment implements MainView {

    @Bind(R.id.rl_progress)     RelativeLayout progressView;
    @Bind(R.id.rl_retry)        RelativeLayout noResultView;
    @Bind(R.id.recycler)        RecyclerView recyclerView;
    @Inject MainPresenter mainPresenter;
    private MovieAdapter movieAdapter;
    private static String MOVIES_STATE = "MOVIES_STATE";
    private List<MovieEntity> movieEntities;


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
        outState.putSerializable(MOVIES_STATE, (Serializable) movieEntities);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null)
            movieEntities = (List<MovieEntity>) savedInstanceState.getSerializable(MOVIES_STATE);
        else if (movieEntities == null)
            movieEntities = new ArrayList<>();

        setRecyclerView();
        initialize();
    }

    private void setRecyclerView() {
        movieAdapter = new MovieAdapter(movieEntities);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void initialize() {
        this.getComponent(FragmentComponent.class).inject(this);
        this.getComponent(FragmentComponent.class).inject(movieAdapter);
        mainPresenter.setMainView(this);
        if (movieEntities.isEmpty())
            mainPresenter.initialize();
    }

    @Override public void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        mainPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mainPresenter.cancelCall();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mainPresenter = null;
        //mMainListener = null;
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
            movieEntities.clear();
        }
    }

    @Override
    public void viewMovies(List<MovieEntity> movies) {
        if (movieAdapter != null) {
            movieAdapter.addAll(movies);
            movieEntities = movies;
        }
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

}