package com.example.cliente.Model;

import java.time.LocalDate;
import java.time.LocalTime;


public class Ingreso {
    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private Servicio servicio;

    private UserEmpleado userEmpleado;

    private long importe;

    //CONSTRUCTORES

    public Ingreso() {
    }

    public Ingreso(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Servicio servicio, UserEmpleado userEmpleado, long importe) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.servicio = servicio;
        this.userEmpleado = userEmpleado;
        this.importe = importe;
    }

    //GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public UserEmpleado getUserEmpleado() {
        return userEmpleado;
    }

    public void setUserEmpleado(UserEmpleado userEmpleado) {
        this.userEmpleado = userEmpleado;
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

    public long getImporte() {
        return importe;
    }

    public void setImporte(long importe) {
        this.importe = importe;
    }
}
