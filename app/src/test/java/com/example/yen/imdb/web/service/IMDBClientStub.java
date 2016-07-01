package com.example.yen.imdb.web.service;

import com.example.yen.imdb.data.response.IMDBResponse;
import com.example.yen.imdb.web.IMDBClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IMDBClientStub extends IMDBClient {

    private Response<IMDBResponse> response;
    private Throwable throwable;


    public IMDBClientStub(IMDBService imdbService) {
        super(imdbService);
    }


    public void getInTheaters(String token) {

        getIMDBService().getInTheaters(token).enqueue(new Callback<IMDBResponse>() {
            @Override
            public void onResponse(Call<IMDBResponse> call, Response<IMDBResponse> response) {
                setResponse(response);
            }

            @Override
            public void onFailure(Call<IMDBResponse> call, Throwable t) {
                setThrowable(t);
            }
        });
    }

    public void setResponse(Response<IMDBResponse> response) {
        this.response = response;
    }

    public Response<IMDBResponse> getResponse() {
        return response;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}