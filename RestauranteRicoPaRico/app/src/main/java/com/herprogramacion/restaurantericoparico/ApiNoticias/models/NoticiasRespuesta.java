package com.herprogramacion.restaurantericoparico.ApiNoticias.models;

import java.util.ArrayList;

/**
 * Created by frati on 25/07/2017.
 */

public class NoticiasRespuesta {
    private ArrayList<Noticias> results;

    public ArrayList<Noticias> getResults() {
        return results;
    }

    public void setResults(ArrayList<Noticias> results) {
        this.results = results;
    }
}
