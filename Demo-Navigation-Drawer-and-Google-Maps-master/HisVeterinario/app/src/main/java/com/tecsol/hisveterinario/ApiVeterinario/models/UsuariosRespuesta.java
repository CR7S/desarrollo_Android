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

package com.tecsol.hisveterinario.ApiVeterinario.models;

import java.util.ArrayList;

/**
 * Created by frati on 25/07/2017.
 */

public class UsuariosRespuesta {
    private ArrayList<Usuarios> results;

    public ArrayList<Usuarios> getResults() {
        return results;
    }

    public void setResults(ArrayList<Usuarios> results) {
        this.results = results;
    }
}

