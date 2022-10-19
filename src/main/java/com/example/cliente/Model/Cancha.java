package com.example.cliente.Model;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class Cancha extends Servicio{
    private int cupos;

    private List<Reserva> reservas;


    //CONSTRUCTORES

    public Cancha() {
    }

    public Cancha(String name, CentroDeportivo centroDeportivoServicio, Long precio, Set<DiasDeLaSemana> dias, String horaInicio, String horaFin, String descripcion, String tipo, int cupos) {
        super(name, centroDeportivoServicio, precio, dias, horaInicio, horaFin, descripcion, tipo);
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
