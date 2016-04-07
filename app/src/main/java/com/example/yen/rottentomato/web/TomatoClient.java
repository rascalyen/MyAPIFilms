package com.example.yen.rottentomato.web;

import com.example.yen.rottentomato.web.configuration.RestClientGenerator;
import com.example.yen.rottentomato.web.service.TomatoService;
import java.util.Properties;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

/**
 * Created by yenhuang on 2/10/16.
 */
public class TomatoClient {

    private final String baseURL;
    private final TomatoService tomatoService;


    @Inject
    public TomatoClient(OkHttpClient okHttpClient, Properties properties) {
        this.baseURL = properties.getProperty("baseURL");
        tomatoService = RestClientGenerator.createService(TomatoService.class, okHttpClient, baseURL);
    }


    public String getBaseURL() {
        return baseURL;
    }

    public TomatoService getTomatoService() {
        return tomatoService;
    }

}
