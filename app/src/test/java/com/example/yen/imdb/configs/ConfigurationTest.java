package com.example.yen.imdb.configs;

import com.example.yen.imdb.RobolectricTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;
import java.util.Properties;
import okhttp3.OkHttpClient;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;


public class ConfigurationTest extends RobolectricTestCase {

    private Configuration config;


    @Before public void setUp() {
        config = new Configuration(RuntimeEnvironment.application);
    }

    @Test public void getOkHttpClient() {
        assertThat(config.getOkHttpClient(), isA(OkHttpClient.class));
    }

    @Test public void getProperties() {
        assertThat(config.getProperties(), isA(Properties.class));
    }

    @After public void tearDown() {
        config = null;
    }

}