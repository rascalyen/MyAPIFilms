package com.example.yen.rottentomato.web.service;

import com.example.yen.rottentomato.data.model.SearchResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by yenhuang on 2/10/16.
 */
public interface TomatoService {

    @GET("/movies.json")
    public void searchMovieTitle(@Query("q") String query, @Query("apikey") String key,
                                 @Query("page_limit") String pageLimit, Callback<SearchResult> callback);

}
