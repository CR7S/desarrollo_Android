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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.tecsol.hisveterinario.modelo.Historial2;


/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public TextView edad;
        public TextView peso;
        public TextView raza;
        public TextView prescripcion;
        public TextView valoracion;


        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
            edad = (TextView) v.findViewById(R.id.edad);
            peso = (TextView) v.findViewById(R.id.peso);
            raza = (TextView) v.findViewById(R.id.raza);
            prescripcion=(TextView) v.findViewById(R.id.prescripcion);
            valoracion=(TextView) v.findViewById(R.id.valoracion);

        }
    }

    public AdaptadorInicio() {
    }

    @Override
    public int getItemCount() {
        return Historial2.POPULARES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Historial2 item = Historial2.POPULARES.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText(item.getfecha());
        viewHolder.peso.setText(item.getPeso());
        viewHolder.edad.setText(item.getEdad());
        viewHolder.raza.setText(item.getRaza());
        viewHolder.valoracion.setText(item.getValoracion());
        viewHolder.prescripcion.setText(item.getPrescripcion());


    }


}