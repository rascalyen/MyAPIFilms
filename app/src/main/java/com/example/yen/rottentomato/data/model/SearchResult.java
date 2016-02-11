package com.example.yen.rottentomato.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yenhuang on 2/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("movies")
    private List<Movie> movies = new ArrayList<>();


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
