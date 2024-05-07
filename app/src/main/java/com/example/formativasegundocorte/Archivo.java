package com.example.formativasegundocorte;

public class Archivo {
    String codigo;
    String nombre;
    String tamaño;
    String tipo;

    public Archivo(String codigo, String nombre, String tamaño, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Archivo{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tamaño='" + tamaño + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
