package com.example.yen.imdb.ui.presenter;

import com.example.yen.imdb.ui.view.ViewMVP;


interface Presenter<T extends ViewMVP> {

    void attachViewMVP(T t);

    void detachViewMVP();
}