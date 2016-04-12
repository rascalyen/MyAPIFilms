package com.example.yen.imdb.data.response;

import com.example.yen.imdb.data.entity.InTheaterEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("inTheaters")
    private List<InTheaterEntity> inTheaters = new ArrayList<>();


    public List<InTheaterEntity> getInTheaters() {
        return inTheaters;
    }

    public void setInTheaters(List<InTheaterEntity> inTheaters) {
        this.inTheaters = inTheaters;
    }

}