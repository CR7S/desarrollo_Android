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

/**
 * Created by frati on 25/07/2017.
 */
public class Historial {
    private String Fecha;
    private String Cedula_Propietario;
    private String Nombre_Propietario;
    private String Mascota;
    private String Peso;
    private String Edad;
    private String Raza;
    private String Foto;
    private String Prescripcion;
    private String Valoracion;

    public String getPrescripcion() {
        return Prescripcion;
    }

    public void setPrescripcion(String prescripcion) {
        Prescripcion = prescripcion;
    }

    public String getValoracion() {
        return Valoracion;
    }

    public void setValoracion(String valoracion) {
        Valoracion = valoracion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getFecha() {
        return Fecha;
    }
    public void setFecha(String Fecha) {
        this.Fecha=Fecha;
    }
    public String getCedula_Propietario() {
        return Cedula_Propietario;
    }
    public void setCedula_Propietario(String Cedula_Propietario) {
        this.Cedula_Propietario=Cedula_Propietario;
    }
    public String getNombre_Propietario() {
        return Nombre_Propietario;
    }
    public void setNombre_Propietario(String Nombre_Propietario) {
        this.Nombre_Propietario=Nombre_Propietario;
    }
    public String getMascota() {
        return Mascota;
    }
    public void setMascota(String Mascota) {
        this.Mascota=Mascota;
    }



}
