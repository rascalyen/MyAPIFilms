package com.example.yen.rottentomato.web.configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by yenhuang on 2/10/16.
 */
public class RestClientGenerator {

    public static <S> S createService(Class<S> serviceClass, OkHttpClient okHttpClient, String baseUrl) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient);

        Retrofit adapter = builder.build();

        return adapter.create(serviceClass);
    }

}
