package com.example.formativasegundocorte;

public class DefBD {
    public static final String nameDB = "Productos";
    public static final String tabla_archivo = "producto";
    public static final String col_cod = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_tamaño = "tamaño";
    public static final String col_tipo = "tipo";

    public static final String crear_tabla = "CREATE TABLE IF NOT EXISTS "
            + DefBD.tabla_archivo + "(" + DefBD.col_cod + " text primary key," +
            DefBD.col_nombre + " text," +
            DefBD.col_tamaño + " text," +
            DefBD.col_tipo + " text);";
}
