package com.example.cliente.DTOs;

import com.example.cliente.Model.DiasDeLaSemana;
import com.example.cliente.Model.Imagen;

import java.time.LocalTime;
import java.util.Set;

public class CanchaDTO extends ServicioDTO{
    private int cupos;

    //CONSTRUCTORES

    public CanchaDTO() {
    }

    public CanchaDTO(String nombreServicio, String nombreCentroDep, String direccion, Long precio, Set<DiasDeLaSemana> dias, LocalTime horaInicio, LocalTime horaFin, String descripcion, String tipo, int cupos) {
        super(nombreServicio, nombreCentroDep, direccion, precio, dias, horaInicio, horaFin, descripcion, tipo);
        this.cupos = cupos;
    }

    public CanchaDTO(String nombreServicio, String nombreCentroDep, String direccion, Long precio, Set<DiasDeLaSemana> dias, LocalTime horaInicio, LocalTime horaFin, String descripcion, String tipo, Set<Imagen> imagenes, int cupos) {
        super(nombreServicio, nombreCentroDep, direccion, precio, dias, horaInicio, horaFin, descripcion, tipo, imagenes);
        this.cupos = cupos;
    }
    //GETTERS Y SETTERS

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }
}
