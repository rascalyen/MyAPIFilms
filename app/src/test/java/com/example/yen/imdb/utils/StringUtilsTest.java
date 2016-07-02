package com.example.yen.imdb.utils;

import com.example.yen.imdb.RobolectricTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.example.yen.imdb.utils.StringUtils.makeMinToHour;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class StringUtilsTest extends RobolectricTestCase {

    private String test1;
    private String test2;


    @Before public void setUp() {
        test1 = "120 min";
        test2 = "97 min";
    }


    @Test public void testCase1() {

        assertEquals("2hr | ", makeMinToHour(test1));
    }

    @Test public void testCase2() {

        assertThat("1hr 37min | ", is(makeMinToHour(test2)));
    }


    @After public void tearDown() {
        test1 = null;
        test2 = null;
    }

}