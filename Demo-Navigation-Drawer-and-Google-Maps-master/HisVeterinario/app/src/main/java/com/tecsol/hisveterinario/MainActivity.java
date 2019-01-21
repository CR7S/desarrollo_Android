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

package com.tecsol.hisveterinario;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.tecsol.hisveterinario.ApiVeterinario.VeterinarioApi.HistorialAPi;
import com.tecsol.hisveterinario.ApiVeterinario.models.Historial;
import com.tecsol.hisveterinario.ApiVeterinario.models.HistorialRespuesta;
import com.tecsol.hisveterinario.ApiVeterinario.models.UsuariosRespuesta;
import com.tecsol.hisveterinario.modelo.Historial2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG="Historial";
    private DrawerLayout drawerLayout;
    private String texto;

    public String getTexto() {
        return texto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("usuario.txt")));

            texto = fin.readLine();
            fin.close();

        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
        Log.e("Ficheros", texto);
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
                                retrofit=new Retrofit.Builder().baseUrl("https://luchoguer.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
                                ObtenerDatos();
                                break;

                            case R.id.action_item3:
                                String[] parts = texto.split("/");
                                String nombre = parts[0]; // 123
                                String direccion= parts[1]; // 654321
                                String telefono= parts[2]; // 654321
                                Perfil.DATOS.clear();
                               Perfil.DATOS.add(new Perfil(nombre,direccion,telefono));
                                selectedFragment = ItemThreeFragment.newInstance(nombre,direccion,telefono);
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


        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
    private void ObtenerDatos() {
        boolean estado;
        HistorialAPi service = retrofit.create(HistorialAPi.class);
        final Call<HistorialRespuesta> HistorialRespuestaCall =service.obtenerHistorial();
        HistorialRespuestaCall.enqueue(new Callback<HistorialRespuesta>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override

            public void onResponse(Call<HistorialRespuesta> call, Response<HistorialRespuesta> response) {
                if (response.isSuccessful()){

                    HistorialRespuesta Resul = response.body();
                    ArrayList<Historial> listaHistorial= Resul.getResults();
                    String[][] matriz=new String[listaHistorial.size()][9];
                    Log.e(TAG,"Historial: "+matriz.length);
                    Historial2.POPULARES.clear();
                    for (int i=0;i<listaHistorial.size();i++){

                        Historial N=listaHistorial.get(i);
                        matriz[i][0]=N.getFecha()+" / "+N.getMascota();
                        matriz[i][1]=N.getCedula_Propietario()+" / "+N.getNombre_Propietario();
                        matriz[i][2]=N.getFoto();
                        matriz[i][3]=N.getFecha();
                        matriz[i][4]=N.getEdad();
                        matriz[i][5]=N.getPeso();
                        matriz[i][6]=N.getRaza();
                        matriz[i][7]=N.getPrescripcion();
                        matriz[i][8]=N.getValoracion();


                        Log.e(TAG,"Historial: "+N.getFoto());

                            Historial2.POPULARES.add(new Historial2(matriz[i][0], matriz[i][1],matriz[i][2],matriz[i][3],matriz[i][4],matriz[i][5],matriz[i][6],matriz[i][7],matriz[i][8]));
                            //Comida.PLATILLOS.add((new Comida(matriz[i][0], matriz[i][1],matriz[i][6],matriz[i][7])));
                            //Comida.BEBIDAS.add((new Comida(matriz[i][0], matriz[i][1],matriz[i][6],matriz[i][7])));
                            //Comida.POSTRES.add((new Comida(matriz[i][0], matriz[i][1],matriz[i][6],matriz[i][7])));
                    }

                }else{
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<HistorialRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure : "+t.getMessage());

            }

        });
    }
}
