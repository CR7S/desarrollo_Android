package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.herprogramacion.restaurantericoparico.ApiNoticias.models.Noticias;
import com.herprogramacion.restaurantericoparico.ApiNoticias.models.NoticiasRespuesta;
import com.herprogramacion.restaurantericoparico.ApiNoticias.pokeapi.NoticiasAPi;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
import com.herprogramacion.restaurantericoparico.pokeapi.PokeapiService;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActividadPrincipal extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG="Noticias";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);



        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            seleccionarItem(navigationView.getMenu().getItem(0));
        }



        //crear e instanciar retrofit desde la url base
        retrofit=new Retrofit.Builder().baseUrl("https://frowsy-levels.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ObtenerDatos();
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

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoCuenta();
                break;
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoCategorias();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
            case R.id.item_mision:
                fragmentoGenerico = new FragmentoMision();
                break;
            case R.id.item_vision:
                fragmentoGenerico = new FragmentoVision();
                break;
            case R.id.item_ubicacion:

                fragmentoGenerico = new FragmentoVision();
                break;

        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
