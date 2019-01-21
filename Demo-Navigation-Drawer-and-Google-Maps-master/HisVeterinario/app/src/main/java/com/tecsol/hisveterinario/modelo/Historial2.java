package com.tecsol.hisveterinario.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Historial2 {
    private String fecha;
    private String nombre;
    private String descripcion;
    private String idDrawable;
    private String edad;
    private String peso;
    private String raza;
    private String prescripcion;
    private String valoracion;


    public Historial2(String fecha, String nombre, String idDrawable, String descripcion,String edad,String peso,String raza,String prescripcion,String valoracion) {

        this.fecha = fecha;
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.idDrawable = idDrawable;
        this.edad=edad;
        this.peso=peso;
        this.raza=raza;
        this.prescripcion=prescripcion;
        this.valoracion=valoracion;
    }

    public static final List<Historial2> POPULARES = new ArrayList<Historial2>();


    static {

    }

    public String getFecha() {
        return fecha;
    }

    public String getPrescripcion() {
        return prescripcion;
    }

    public String getValoracion() {
        return valoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getfecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public String getEdad() {  return edad;    }

    public String getPeso() {        return peso;    }

    public String getRaza() {        return raza;    }
}
