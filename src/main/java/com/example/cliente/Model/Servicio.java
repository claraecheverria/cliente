package com.example.cliente.Model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;


public class Servicio {

    private ServicioIdNew key;

    private CentroDeportivo centroDeportivoServicio;
    private Long precio;

    private Set<DiasDeLaSemana> dias;

    private String horaInicio;
    private String horaFin;
    private String descripcion;
    private String tipo;

    private List<UserEmpleado> favoritos;

    private Set<Imagen> imagenes;

    List<Ingreso> ingresos;


    //CONSTRUCTORES
    public Servicio() {
    }
    public Servicio(String name, CentroDeportivo centroDeportivoServicio, Long precio, Set<DiasDeLaSemana> dias, String horaInicio, String horaFin, String descripcion, String tipo) {
        this.key = new ServicioIdNew();
        this.key.setNombre(name);
        this.key.setCentroDeportivo(centroDeportivoServicio.getNombre());
        this.centroDeportivoServicio = centroDeportivoServicio;
        this.precio = precio;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
    public Servicio(String name, CentroDeportivo centroDeportivoServicio, Long precio, Set<DiasDeLaSemana> dias, String horaInicio, String horaFin, String descripcion, String tipo, Set<Imagen> imagenes) {
        this.key = new ServicioIdNew();
        this.key.setNombre(name);
        this.key.setCentroDeportivo(centroDeportivoServicio.getNombre());
        this.centroDeportivoServicio = centroDeportivoServicio;
        this.precio = precio;
        this.dias = dias;
        this.horaInicio = String.valueOf(horaInicio);
        this.horaFin = String.valueOf(horaFin);
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagenes = imagenes;
    }
    public Servicio(ServicioIdNew key, Long precio, Set<DiasDeLaSemana> dias, String horaInicio, String horaFin, String descripcion, String tipo) {
        this.key = new ServicioIdNew();
        this.precio = precio;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
    //GETTERS Y SETTERS

    public ServicioIdNew getKey() {
        return key;
    }

    public void setKey(ServicioIdNew key) {
        this.key = key;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
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

    public List<UserEmpleado> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<UserEmpleado> favoritos) {
        this.favoritos = favoritos;
    }


    public CentroDeportivo getCentroDeportivoServicio() {
        return centroDeportivoServicio;
    }

    public void setCentroDeportivoServicio(CentroDeportivo centroDeportivoServicio) {
        this.centroDeportivoServicio = centroDeportivoServicio;
    }

    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }
}
