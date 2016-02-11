package com.example.yen.rottentomato.ui.presenter;

/**
 * Created by yenhuang on 2/10/16.
 */
interface Presenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

}
