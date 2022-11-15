package com.example.cliente.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class IngresoDTO {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String servicioNombre;
    private String centroDepNombre;
    private String emailUserEmpleado;
    private long importe;

    //CONSTRUCTORES

    public IngresoDTO() {
    }

    public IngresoDTO(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String servicioNombre, String centroDepNombre, String emailUserEmpleado, long importe) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.servicioNombre = servicioNombre;
        this.centroDepNombre = centroDepNombre;
        this.emailUserEmpleado = emailUserEmpleado;
        this.importe = importe;
    }

    //GETTERS Y SETTERS


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public String getCentroDepNombre() {
        return centroDepNombre;
    }

    public void setCentroDepNombre(String centroDepNombre) {
        this.centroDepNombre = centroDepNombre;
    }

    public String getEmailUserEmpleado() {
        return emailUserEmpleado;
    }

    public void setEmailUserEmpleado(String emailUserEmpleado) {
        this.emailUserEmpleado = emailUserEmpleado;
    }

    public long getImporte() {
        return importe;
    }

    public void setImporte(long importe) {
        this.importe = importe;
    }
}
