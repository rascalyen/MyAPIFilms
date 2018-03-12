package com.example.yen.imdb.data.web.response;

import com.example.yen.imdb.data.model.InTheater;
import java.util.ArrayList;
import java.util.List;


public class Data {

    private List<InTheater> inTheaters = new ArrayList<>();


    public List<InTheater> getInTheaters() {
        return inTheaters;
    }

    public void setInTheaters(List<InTheater> inTheaters) {
        this.inTheaters = inTheaters;
    }

}