package com.example.yen.imdb.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class InTheaterEntity {

    @JsonProperty("openingThisWeek")
    private String openingThisWeek;
    @JsonProperty("inTheatersNow")
    private String inTheatersNow;
    @JsonProperty("movies")
    private List<MovieEntity> movies = new ArrayList<>();


    public String getOpeningThisWeek() {
        return openingThisWeek;
    }

    public void setOpeningThisWeek(String openingThisWeek) {
        this.openingThisWeek = openingThisWeek;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public String getInTheatersNow() {
        return inTheatersNow;
    }

    public void setInTheatersNow(String inTheatersNow) {
        this.inTheatersNow = inTheatersNow;
    }

}