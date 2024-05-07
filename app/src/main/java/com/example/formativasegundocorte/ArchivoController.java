package com.example.formativasegundocorte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ArchivoController {
    private BaseDatos bd;
    private Context c;

    public ArchivoController(Context c){
        this.bd = new BaseDatos(c,DefBD.nameDB,1);
        this.c = c;
    }

    public void agregarArchivo(Archivo a){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_cod, a.getCodigo());
            valores.put(DefBD.col_nombre, a.getNombre());
            valores.put(DefBD.col_tamaño, a.getTamaño());
            valores.put(DefBD.col_tipo, a.getTipo());

            if (!buscarArchivo(a)) {
                sql.insert(DefBD.tabla_archivo, null, valores);
                Toast.makeText(c, "Registro exitosa", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(c, "El codigo del archivo ya existe", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(c, "Error en la operacion " + ex.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    public boolean buscarArchivo(Archivo a){
        String[] arg = new String[] {a.getCodigo()};

        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(DefBD.tabla_archivo, null,"codigo=?", arg,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            return false;
        }
    }

    public Archivo buscarArchivo(String codigo){
        String[] arg = new String[] {codigo};
        Archivo a = null;
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(DefBD.tabla_archivo, null,"codigo=?", arg,null,null,null);
        if (c.getCount()>0){
            while(c.moveToNext()) {
                a = new Archivo(c.getString(0), c.getString(1), c.getString(2)
                        , c.getString(3));

            }
            return a;
        }
        else{
            return a;
        }
    }

    public void actualizarArchivo(Archivo a){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            String arg[] = {a.getCodigo()};
            ContentValues values = new ContentValues();
            values.put(DefBD.col_nombre, a.getNombre());
            values.put(DefBD.col_tamaño, a.getTamaño());
            values.put(DefBD.col_tipo, a.getTipo());
            sql.update(DefBD.tabla_archivo, values,"codigo=?",arg);
            Toast.makeText(this.c, "Actualizacion exitosa", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(c, "Error en la operacion " + e.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarArchivo(String codigo){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            String[] arg = new String[] {codigo};
            sql.delete(DefBD.tabla_archivo,"codigo=?", arg);
            Toast.makeText(this.c, "Eliminación exitosa", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error en la operacion " + ex.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor allFiles(){
        Cursor cur;
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            cur = sql.rawQuery("select codigo as _id, nombre, tamaño, tipo from producto order by " + DefBD.col_nombre, null);
            return cur;
        }
        catch (Exception e){
            Toast.makeText(c, "Error en la operacion " + e.getMessage() , Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}