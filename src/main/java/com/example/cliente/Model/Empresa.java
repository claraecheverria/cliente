package com.example.cliente.Model;

public class Empresa {
    private String nombre;
    private String RUT;
    private String razonSocial;
    private String Direccion;

    public Empresa(String nombre, String rut, String razonSocial, String direccion) {
        this.nombre = nombre;
        this.RUT = rut;
        this.razonSocial = razonSocial;
        this.Direccion = direccion;
    }

    public Empresa() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
}
