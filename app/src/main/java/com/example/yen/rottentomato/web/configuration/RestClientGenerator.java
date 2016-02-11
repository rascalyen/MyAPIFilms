package com.example.yen.rottentomato.web.configuration;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/**
 * Created by yenhuang on 2/10/16.
 */
public class RestClientGenerator {

    public static <S> S createService(Class<S> serviceClass, OkHttpClient okHttpClient, String baseUrl) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setConverter(new JacksonConverter())
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setClient(new OkClient( okHttpClient ));

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }

}
