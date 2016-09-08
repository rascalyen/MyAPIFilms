package com.example.yen.imdb.web;

import com.example.yen.imdb.RobolectricTestCase;
import com.example.yen.imdb.configs.Configuration;
import com.example.yen.imdb.web.service.IMDBService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;


public class IMDBClientTest extends RobolectricTestCase {

    private Configuration configs;
    private IMDBClient imdbClient;


    @Before public void setUp() {
        configs = new Configuration(RuntimeEnvironment.application);
        imdbClient = new IMDBClient(configs.getOkHttpClient(), configs.getProperties());
    }

    @Test public void getIMDBService() {
        assertThat(imdbClient.getImdbService(), isA(IMDBService.class));
    }

    @After public void tearDown() {
        configs = null;
        imdbClient = null;
    }

}