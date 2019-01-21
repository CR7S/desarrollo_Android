package com.herprogramacion.restaurantericoparico.ApiNoticias.pokeapi;

import com.herprogramacion.restaurantericoparico.ApiNoticias.models.NoticiasRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by frati on 25/07/2017.
 */

public interface NoticiasAPi {
    @GET("PjNoticias.php")
    Call<NoticiasRespuesta> obtenerListaPokemon();
}
