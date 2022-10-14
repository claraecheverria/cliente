package com.example.cliente.Model;

public class Servicio {

    private String nombre;
    private int precio;
    private String horarios;
    private String direccion;
    private String ImageScr;

    private String tipo;

    public Servicio(String nombre, int precio, String horarios,String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.horarios = horarios;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImageScr() {
        return ImageScr;
    }

    public void setImageScr(String imageScr) {
        ImageScr = imageScr;
    }
}
