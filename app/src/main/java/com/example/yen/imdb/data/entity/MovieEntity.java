package com.example.yen.imdb.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieEntity {

    @JsonProperty("title")
    private String title;
    @JsonProperty("originalTitle")
    private String originalTitle;
    @JsonProperty("year")
    private String year;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("directors")
    private List<PersonEntity> directors = new ArrayList<>();
    @JsonProperty("writers")
    private List<PersonEntity> writers = new ArrayList<>();
    @JsonProperty("runtime")
    private String runtime;
    @JsonProperty("urlPoster")
    private String urlPoster;
    @JsonProperty("countries")
    private List<String> countries = new ArrayList<>();
    @JsonProperty("languages")
    private List<String> languages = new ArrayList<>();
    @JsonProperty("genres")
    private List<String> genres = new ArrayList<>();
    @JsonProperty("plot")
    private String plot;
    @JsonProperty("simplePlot")
    private String simplePlot;
    @JsonProperty("idIMDB")
    private String idIMDB;
    @JsonProperty("urlIMDB")
    private String urlIMDB;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("metascore")
    private String metascore;
    @JsonProperty("filmingLocations")
    private List<String> filmingLocations = new ArrayList<>();
    @JsonProperty("rated")
    private String rated;
    @JsonProperty("votes")
    private String votes;
    @JsonProperty("type")
    private String type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<PersonEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonEntity> directors) {
        this.directors = directors;
    }

    public List<PersonEntity> getWriters() {
        return writers;
    }

    public void setWriters(List<PersonEntity> writers) {
        this.writers = writers;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getSimplePlot() {
        return simplePlot;
    }

    public void setSimplePlot(String simplePlot) {
        this.simplePlot = simplePlot;
    }

    public String getIdIMDB() {
        return idIMDB;
    }

    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    public String getUrlIMDB() {
        return urlIMDB;
    }

    public void setUrlIMDB(String urlIMDB) {
        this.urlIMDB = urlIMDB;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public List<String> getFilmingLocations() {
        return filmingLocations;
    }

    public void setFilmingLocations(List<String> filmingLocations) {
        this.filmingLocations = filmingLocations;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}