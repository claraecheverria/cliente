package com.example.cliente.Model;


import java.io.Serializable;


public class ServicioIdNew implements Serializable {
    private String nombre;

    private String centroDeportivo;

    public ServicioIdNew() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(String centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
