package com.herprogramacion.restaurantericoparico.pokeapi;

import com.herprogramacion.restaurantericoparico.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Daniel Alvarez on 28/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon();

}
