package com.herprogramacion.restaurantericoparico.SQlite;

/**
 * Created by frati on 25/07/2017.
 */

public class SqliteCrear {
    protected static String TableUsuarios = "usuarios";
    protected static String TableLibros = "libros";

    private String SQLCreateUsuarios = "CREATE TABLE " + TableUsuarios +  " (id_usuario INT, nombre VARCHAR(1000), email VARCHAR(1000) ) ";
    private String SQLCreateLibros = "CREATE TABLE " + TableLibros +  " (id_libro INT, nombre VARCHAR(1000), editorial VARCHAR(1000) )";


}
