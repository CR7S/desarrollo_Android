package com.tecsol.hisveterinario;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.tecsol.hisveterinario.ApiVeterinario.VeterinarioApi.HistorialAPi;

import com.tecsol.hisveterinario.ApiVeterinario.models.Historial;
import com.tecsol.hisveterinario.ApiVeterinario.models.HistorialRespuesta;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inicio extends AppCompatActivity {
    private Retrofit retrofit;

    private static final String TAG="Historial2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        retrofit=new Retrofit.Builder().baseUrl("https://frowsy-levels.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        ObtenerDatos();


    }

    private void ObtenerDatos() {
        boolean estado;
        HistorialAPi service = retrofit.create(HistorialAPi.class);
        Call<HistorialRespuesta> HistorialRespuestaCall =service.obtenerHistorial();
        HistorialRespuestaCall.enqueue(new Callback<HistorialRespuesta>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<HistorialRespuesta> call, Response<HistorialRespuesta> response) {
                if (response.isSuccessful()){
                    HistorialRespuesta Resul = response.body();
                    ArrayList<Historial> listaHistorial= Resul.getResults();
                    String[][] matriz=new String[listaHistorial.size()][9];
                    for (int i=0;i<listaHistorial.size();i++){
                        Historial N=listaHistorial.get(i);
                        Log.e(TAG,N.getCedula_Propietario()+" - "+N.getFecha()+" - "+N.getMascota());
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
