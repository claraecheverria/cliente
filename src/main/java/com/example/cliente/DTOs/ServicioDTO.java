package com.example.cliente.DTOs;

import com.example.cliente.Model.DiasDeLaSemana;
import com.example.cliente.Model.Imagen;

import java.time.LocalTime;
import java.util.Set;

public class ServicioDTO {
    private String nombreServicio;
    private String nombreCentroDep;
    private String direccion;
    private Long precio;
    private Set<DiasDeLaSemana> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String descripcion;
    private String tipo;
    private Set<Imagen> imagenes;
    //favoritos e ingresos no

    //CONSTRUCTORES

    public ServicioDTO() {
    }

    public ServicioDTO(String nombreServicio, String nombreCentroDep, String direccion, Long precio, Set<DiasDeLaSemana> dias, LocalTime horaInicio, LocalTime horaFin, String descripcion, String tipo) {
        this.nombreServicio = nombreServicio;
        this.nombreCentroDep = nombreCentroDep;
        this.direccion = direccion;
        this.precio = precio;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public ServicioDTO(String nombreServicio, String nombreCentroDep, String direccion, Long precio, Set<DiasDeLaSemana> dias, LocalTime horaInicio, LocalTime horaFin, String descripcion, String tipo, Set<Imagen> imagenes) {
        this.nombreServicio = nombreServicio;
        this.nombreCentroDep = nombreCentroDep;
        this.direccion = direccion;
        this.precio = precio;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagenes = imagenes;
    }
    //GETTERS Y SETTERS

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getNombreCentroDep() {
        return nombreCentroDep;
    }

    public void setNombreCentroDep(String nombreCentroDep) {
        this.nombreCentroDep = nombreCentroDep;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Set<DiasDeLaSemana> getDias() {
        return dias;
    }

    public void setDias(Set<DiasDeLaSemana> dias) {
        this.dias = dias;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
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

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
