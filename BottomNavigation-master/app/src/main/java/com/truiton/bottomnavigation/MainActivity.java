/*
 * Copyright (c) 2016. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.truiton.bottomnavigation;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.truiton.bottomnavigation.ApiNoticias.models.Noticias;
import com.truiton.bottomnavigation.ApiNoticias.models.NoticiasRespuesta;
import com.truiton.bottomnavigation.ApiNoticias.pokeapi.NoticiasAPi;
import com.truiton.bottomnavigation.modelo.Comida;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG="Noticias";
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = FragmentoInicio.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = ItemfourFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();
        retrofit=new Retrofit.Builder().baseUrl("https://frowsy-levels.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ObtenerDatos();
        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
    private void ObtenerDatos() {

        NoticiasAPi service = retrofit.create(NoticiasAPi.class);
        Call<NoticiasRespuesta> noticiasRespuestaCall =service.obtenerListaPokemon();
        noticiasRespuestaCall.enqueue(new Callback<NoticiasRespuesta>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<NoticiasRespuesta> call, Response<NoticiasRespuesta> response) {
                if (response.isSuccessful()){
                    NoticiasRespuesta pokemonRespuesta = response.body();
                    ArrayList<Noticias> listaPokemon = pokemonRespuesta.getResults();
                    String[][] matriz=new String[listaPokemon.size()][9];
                    for (int i=0;i<listaPokemon.size();i++){

                        Noticias N=listaPokemon.get(i);
                        matriz[i][0]=N.getNombre();
                        matriz[i][1]=N.getDescripcion();
                        matriz[i][2]=N.getEncargado();
                        matriz[i][3]=N.getLugar();
                        matriz[i][4]=N.getTelefono();
                        matriz[i][5]=N.getTipo();
                        matriz[i][6]=N.getImagen();
                        matriz[i][7]=N.getFecha();

                        Log.e(TAG,"Noticias: "+N.getTipo());

                        if(Objects.equals(N.getTipo(), "1")){
                            Comida.PLATILLOS.add((new Comida(matriz[i][7], matriz[i][0],matriz[i][6],matriz[i][1])));
                        }
                        if(Objects.equals(N.getTipo(), "2")){
                            Comida.BEBIDAS.add((new Comida(matriz[i][7], matriz[i][0],matriz[i][6],matriz[i][1])));

                        }
                        if(Objects.equals(N.getTipo(), "3")){
                            Comida.POSTRES.add((new Comida(matriz[i][7], matriz[i][0], matriz[i][6],matriz[i][1])));

                        }if(Objects.equals(N.getTipo(), "4")){
                            Comida.COMIDAS_POPULARES.add((new Comida(matriz[i][7], matriz[i][0], matriz[i][6],matriz[i][1])));

                        }



                    }

                }else{
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<NoticiasRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure : "+t.getMessage());

            }

        });
    }
}
