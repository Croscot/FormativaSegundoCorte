package com.example.formativasegundocorte;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cod, nombre, tamaño, tipo;
    Button guardar, consultar, eliminar, listado, actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cod = (EditText) findViewById(R.id.edtcodarchivos);
        nombre = (EditText) findViewById(R.id.edtnombrearchivo);
        tamaño = (EditText) findViewById(R.id.edttamaño);
        tipo = (EditText) findViewById(R.id.edttipo);
        guardar = (Button) findViewById(R.id.btnguardar);
        consultar = (Button) findViewById(R.id.btnconsultar);
        actualizar = (Button) findViewById(R.id.btnactualizar);
        eliminar = (Button) findViewById(R.id.btneliminar);
        listado = (Button) findViewById(R.id.btnlistado);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarEntrada()) {
                    Archivo a = new Archivo(cod.getText().toString(), nombre.getText().toString(),
                            tamaño.getText().toString(), tipo.getText().toString());
                    ArchivoController pc = new ArchivoController(getApplicationContext());
                    pc.agregarArchivo(a);
                    limpiarCampos();
                }
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cod.getText().toString().isEmpty()) {
                    ArchivoController pc = new ArchivoController(getApplicationContext());
                    Archivo a = pc.buscarArchivo(cod.getText().toString());
                    if (a != null) {
                        nombre.setText(a.getNombre());
                        tamaño.setText(a.getTamaño());
                        tipo.setText(a.getTipo());
                    } else {
                        Toast.makeText(getApplicationContext(), "Archivo no encontrado", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese codigo de Archivo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cod.getText().toString().isEmpty()) {
                    ArchivoController pc = new ArchivoController(getApplicationContext());
                    pc.eliminarArchivo(cod.getText().toString());
                    limpiarCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese codigo de Archivo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListadoArchivos.class);
                startActivity(i);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarEntrada()) {
                    ArchivoController pc = new ArchivoController(getApplicationContext());
                    Archivo p = new Archivo(cod.getText().toString(), nombre.getText().toString(),
                            tamaño.getText().toString(), tipo.getText().toString());
                    pc.actualizarArchivo(p);
                    limpiarCampos();
                }
            }
        });
    }

    private boolean validarEntrada() {
        if (cod.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() ||
                tamaño.getText().toString().isEmpty() || tipo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        cod.setText("");
        nombre.setText("");
        tamaño.setText("");
        tipo.setText("");
    }
}