/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
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

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tecsol.hisveterinario.modelo.Historial2;

import java.util.Objects;


/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorPerfil
        extends RecyclerView.Adapter<AdaptadorPerfil.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView telefono;
        public TextView direccion;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            telefono = (TextView) v.findViewById(R.id.telefono);
            direccion = (TextView) v.findViewById(R.id.direccion);
        }
    }

    public AdaptadorPerfil() {
    }

    @Override
    public int getItemCount() {
        return Perfil.DATOS.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Perfil item = Perfil.DATOS.get(i);
        MainActivity lo=new MainActivity();

        String user=lo.getTexto();
        String contra=item.getNombre();
        if (user==contra){
            viewHolder.nombre.setText(item.getNombre());
            viewHolder.telefono.setText(item.getTelefono());
            viewHolder.direccion.setText(item.getDireccion());
        }
        Log.e("Usuario_activo", lo.getTexto());


    }


}