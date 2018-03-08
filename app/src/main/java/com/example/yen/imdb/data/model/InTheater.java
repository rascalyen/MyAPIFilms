package com.example.yen.imdb.data.model;

import java.util.ArrayList;
import java.util.List;


public class InTheater {

    private String openingThisWeek;
    private String inTheatersNow;
    private List<Movie> movies = new ArrayList<>();


    public String getOpeningThisWeek() {
        return openingThisWeek;
    }

    public void setOpeningThisWeek(String openingThisWeek) {
        this.openingThisWeek = openingThisWeek;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getInTheatersNow() {
        return inTheatersNow;
    }

    public void setInTheatersNow(String inTheatersNow) {
        this.inTheatersNow = inTheatersNow;
    }

}