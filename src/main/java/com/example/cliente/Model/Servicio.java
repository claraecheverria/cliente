package com.example.cliente.Model;

public class Servicio {

    private String nombre;
    private int precio;
    private String horarios;
    private int cupos;

    public Servicio(String nombre, int precio, String horarios, int cupos) {
        this.nombre = nombre;
        this.precio = precio;
        this.horarios = horarios;
        this.cupos = cupos;
    }
}
