package com.example.yen.rottentomato.utils;

/**
 * Created by yenhuang on 2/11/16.
 */
public abstract class StringUtils {

    public static String encodeSpace(String s) {

        return s.replaceAll(" ", "%20");
    }
}
