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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Intent.getIntent;


public class ItemThreeFragment extends Fragment {
    private static String nombre;
    private static String dire;
    private static String tele;

    public static ItemThreeFragment newInstance(String nomb,String d,String t) {
        ItemThreeFragment fragment = new ItemThreeFragment();
        nombre=nomb;
        dire=d;
        tele=t;


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_item_three, container, false);
        TextView nomb = (TextView) v.findViewById(R.id.nombre);
        nomb.setText(nombre);
        TextView telefono=(TextView)v.findViewById(R.id.telefono);
        telefono.setText(tele);
        TextView direccion=(TextView)v.findViewById(R.id.direccion);
        direccion.setText(dire);
        return v;


    }
}
