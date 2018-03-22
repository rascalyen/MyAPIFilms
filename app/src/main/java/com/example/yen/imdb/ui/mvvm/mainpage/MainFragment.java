package com.example.yen.imdb.ui.mvvm.mainpage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.yen.imdb.R;
import com.example.yen.imdb.configs.dagger.component.ActivityComponent;
import com.example.yen.imdb.ui.BaseFragment;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends BaseFragment {

    @BindView(R.id.rl_progress)
    RelativeLayout progressView;
    @BindView(R.id.rl_retry)
    RelativeLayout noResultView;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @Inject MovieAdapter movieAdapter;
    private MainViewModel viewModel;
    private Unbinder unbinder;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        super();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        injectComponent();
        setupRecyclerView();
        observeUI();
        initialize();
    }

    private void injectComponent() {
        this.getComponent(ActivityComponent.class).inject(this);
        this.getComponent(ActivityComponent.class).inject(viewModel);
    }

    private void setupRecyclerView() {
        movieAdapter.setMovies(viewModel.getMoviesObservable().getValue());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void observeUI() {

        viewModel.getProgressObservable().observe(this, isLoading -> {
            progressView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            getActivity().setProgressBarIndeterminateVisibility(isLoading);
        });

        viewModel.getRetryObservable().observe(this,
                isRetry -> noResultView.setVisibility(isRetry ? View.VISIBLE : View.GONE));

        viewModel.getMoviesObservable().observe(this, movies -> {
            if (noResultView.getVisibility() == View.GONE)
                movieAdapter.addAll(new ArrayList<>(movies));
        });

        viewModel.getErrorMsgObservable().observe(this, this::showToastMessage);
    }

    private void initialize() {

        if (movieAdapter.getMovies().isEmpty())
            viewModel.initialize();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        viewModel.cancelCall();
    }

    public void clearMovies() {
        if (movieAdapter != null)   movieAdapter.clearAll();
    }

    public MainViewModel getMainViewModel() {
        return viewModel;
    }

}