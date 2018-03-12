package com.example.yen.imdb.data.web.api;

import com.example.yen.imdb.data.web.response.IMDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IMDBService {

    @GET("inTheaters")
    Call<IMDBResponse> getInTheaters(@Query("token") String apiKey, @Query("format") String format,
                                     @Query("language") String language);

}