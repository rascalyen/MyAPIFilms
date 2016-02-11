package com.example.yen.rottentomato.ui.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.yen.rottentomato.R;
import com.example.yen.rottentomato.data.model.Movie;
import com.example.yen.rottentomato.ui.view.adapter.RecyclerAdapter;
import com.example.yen.rottentomato.ui.dependency.component.FragmentComponent;
import com.example.yen.rottentomato.ui.presenter.ResultPresenter;
import com.example.yen.rottentomato.ui.view.ResultView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultFragment extends BaseFragment implements ResultView {

    private static final String ARG_QUERY     = "QUERY";
    @Bind(R.id.rl_progress)     RelativeLayout progressView;
    @Bind(R.id.rl_retry)        RelativeLayout noResultView;
    @Bind(R.id.recycler)        RecyclerView recyclerView;
    @Inject ResultPresenter resultPresenter;
    private RecyclerAdapter listAdapter;
    private ResultListener mResultListener;


    public static ResultFragment newInstance(String query) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        resultFragment.setArguments(args);
        return resultFragment;
    }

    public ResultFragment() {
        super();
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mResultListener = (ResultListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BasicListener");
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listAdapter = new RecyclerAdapter(this.getContext(), new ArrayList<Movie>());
        listAdapter.setOnItemClickListener(new RecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                mResultListener.moveToDetail(listAdapter.getItem(position));
            }
        });
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        this.getComponent(FragmentComponent.class).inject(this);
        this.getComponent(FragmentComponent.class).inject(listAdapter);
        resultPresenter.setResultView(this);
        resultPresenter.initialize(getArguments().getString(ARG_QUERY));
    }

    @Override public void onResume() {
        super.onResume();
        resultPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        resultPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        resultPresenter = null;
        mResultListener = null;
    }

    @Override
    public void viewListResult(List<Movie> movies) {
        listAdapter.addAll(movies);
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


    public interface ResultListener {
        void moveToDetail(Movie movie);
    }

}
