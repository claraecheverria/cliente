package com.example.cliente.Model;

public class Servicio {

    private String nombre;
    private Long precio;
    private String horario;
    private String descripcion;
    private String tipo; //este va a tener una opcion para seleccionar cuando se cree para hacer luego los filtros por tipo
    private CentroDeportivo centroDeportivoServicio;

    //CONSTRUCTORES

    public Servicio() {
    }

    public Servicio(String nombre, Long precio, String horario, String descripcion, String tipo, CentroDeportivo centroDeportivoServicio) {
        this.nombre = nombre;
        this.precio = precio;
        this.horario = horario;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.centroDeportivoServicio = centroDeportivoServicio;
    }

    //GETTERS Y SETTERS


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public CentroDeportivo getCentroDeportivoServicio() {
        return centroDeportivoServicio;
    }

    public void setCentroDeportivoServicio(CentroDeportivo centroDeportivoServicio) {
        this.centroDeportivoServicio = centroDeportivoServicio;
    }
}
