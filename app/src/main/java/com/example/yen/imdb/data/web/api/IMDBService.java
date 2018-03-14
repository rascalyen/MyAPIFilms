package com.example.yen.imdb.data.web.api;

import com.example.yen.imdb.data.web.response.IMDBResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IMDBService {

    @GET("inTheaters")
    Observable<IMDBResponse> getInTheaters(@Query("token") String apiKey,
                                           @Query("format") String format,
                                           @Query("language") String language);

}