package com.example.cliente.Model;


public class CentroDeportivo {
    private String nombre;
    private String RUT;
    private String razonSocial;
    private String direccion;

    public CentroDeportivo() {
    }

    public CentroDeportivo(String nombre, String rut, String razonSocial, String direccion) {
        this.nombre = nombre;
        this.RUT = rut;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
