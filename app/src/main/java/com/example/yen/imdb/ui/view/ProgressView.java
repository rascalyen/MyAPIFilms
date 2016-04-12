package com.example.yen.imdb.ui.view;

import android.content.Context;


public interface ProgressView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showProgress();

    /**
     * Hide a loading view.
     */
    void hideProgress();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetry();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetry();

    /**
     * Show a message
     *
     * @param message A string representing an error.
     */
    void showMessage(String message);

    /**
     * Get a {@link android.content.Context}.
     */
    Context getContext();

}
