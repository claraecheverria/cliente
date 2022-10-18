package com.example.cliente.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Reserva {

    private Long id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private Cancha cancha;

    private List<UserEmpleado> usuariosInvitados;
}
