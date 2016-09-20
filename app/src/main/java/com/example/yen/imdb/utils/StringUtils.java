package com.example.yen.imdb.utils;


public abstract class StringUtils {

    public static String makeMinToHour(String s) {

        if (s != null && !s.isEmpty()) {
            try {
                int minute = Integer.parseInt(s.substring(0, s.indexOf(' ')));
                int hour = minute / 60;
                minute = minute % 60;

                return (minute == 0) ? hour + "hr | " : hour + "hr " + minute + "min" + " | ";
            }
            catch (NumberFormatException ex) {
                return "";
            }
        }
        return "";
    }

}