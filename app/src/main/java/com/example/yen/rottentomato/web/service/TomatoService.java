package com.example.yen.rottentomato.web.service;

import com.example.yen.rottentomato.data.model.SearchResult;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yenhuang on 2/10/16.
 */
public interface TomatoService {

    @GET("inTheaters")
    public void getInTheaters(@Query("token") String apiKey, Callback<SearchResult> callback);

}
