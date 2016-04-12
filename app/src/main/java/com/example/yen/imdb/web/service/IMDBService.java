package com.example.yen.imdb.web.service;

import com.example.yen.imdb.data.response.IMDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IMDBService {

    @GET("inTheaters")
    Call<IMDBResponse> getInTheaters(@Query("token") String apiKey);

}