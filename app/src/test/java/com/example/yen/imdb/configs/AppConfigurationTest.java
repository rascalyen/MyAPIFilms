package com.example.yen.imdb.configs;

import com.example.yen.imdb.RobolectricTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;
import java.util.Properties;
import okhttp3.Cache;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;


public class AppConfigurationTest extends RobolectricTestCase {

    private AppConfiguration config;


    @Before public void setUp() {
        config = new AppConfiguration(RuntimeEnvironment.application);
    }

    @Test public void getCache() {
        assertThat(config.getCache(), isA(Cache.class));
    }

    @Test public void getProperties() {
        assertThat(config.getProperties(), isA(Properties.class));
    }

    @After public void tearDown() {
        config = null;
    }

}