package com.example.cliente.Model;


public class Imagen {
    private Long id;

    private String imagen;

    private Servicio servicio;


    public Imagen() {
    }

    public Imagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
