package com.example.formativasegundocorte;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListadoArchivos extends AppCompatActivity {

    ListView listado;
    ArchivoCursorAdapter pcur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_archivos);
        listado = (ListView) findViewById(R.id.lstlistado);
        ArchivoController pc = new ArchivoController(this);
        Cursor c = pc.allFiles();
        if (c.getCount()>0) {
            pcur = new ArchivoCursorAdapter(this,c,false);
            listado.setAdapter(pcur);
            listado.setTextFilterEnabled(true);
            pcur.setFilterQueryProvider(new FilterQueryProvider() {
                @Override
                public Cursor runQuery(CharSequence constraint) {
                    return null;
                }
            });
        }
        else
            Toast.makeText(getApplicationContext(), "No hay datos",Toast.LENGTH_SHORT).show();
    }
}