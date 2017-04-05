package com.example.yen.imdb.web;

import com.example.yen.imdb.web.configuration.RestClientGenerator;
import com.example.yen.imdb.web.service.IMDBService;
import javax.inject.Inject;
import okhttp3.OkHttpClient;


public class IMDBClient {

    private final IMDBService imdbService;


    @Inject
    public IMDBClient(OkHttpClient okHttpClient, String baseUrl) {
        imdbService = RestClientGenerator.createService(IMDBService.class, okHttpClient, baseUrl);
    }

    public IMDBClient(IMDBService imdbService) {
        this.imdbService = imdbService;
    }

    public IMDBService getImdbService() {
        return imdbService;
    }

}