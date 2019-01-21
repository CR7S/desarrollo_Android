package com.tecsol.hisveterinario;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Perfil {
    private String nombre;
    private String telefono;
    private String direccion;


    public Perfil(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono=telefono;
        this.direccion = direccion;
    }

    public static final List<Perfil> DATOS = new ArrayList<Perfil>();


    public String getTelefono() {
        return telefono;
    }

    public String getDireccion(){ return direccion;}

    public String getNombre() {
        return nombre;
    }


}
