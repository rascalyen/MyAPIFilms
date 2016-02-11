package com.example.yen.rottentomato.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by yenhuang on 2/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poster implements Serializable {

    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("profile")
    private String profile;
    @JsonProperty("detailed")
    private String detailed;
    @JsonProperty("original")
    private String original;


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
