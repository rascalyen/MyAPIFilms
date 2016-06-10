package com.example.yen.imdb.ui;


public interface Presenter<T extends ViewMVP> {

    void attachViewMVP(T t);

    void detachViewMVP();
}