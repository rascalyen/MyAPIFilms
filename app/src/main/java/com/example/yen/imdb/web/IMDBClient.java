package com.example.yen.imdb.web;

import com.example.yen.imdb.web.configuration.RestClientGenerator;
import com.example.yen.imdb.web.service.IMDBService;
import java.util.Properties;
import javax.inject.Inject;
import okhttp3.OkHttpClient;


public class IMDBClient {

    private final String baseURL;
    private final IMDBService imdbService;


    @Inject
    public IMDBClient(OkHttpClient okHttpClient, Properties properties) {
        this.baseURL = properties.getProperty("baseURL");
        imdbService = RestClientGenerator.createService(IMDBService.class, okHttpClient, baseURL);
    }

    public IMDBClient(IMDBService imdbService) {
        this.imdbService = imdbService;
        this.baseURL = null;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public IMDBService getImdbService() {
        return imdbService;
    }

}