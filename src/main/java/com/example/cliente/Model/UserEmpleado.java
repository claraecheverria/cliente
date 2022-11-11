package com.example.cliente.Model;

import java.time.LocalDate;
import java.util.List;


public class UserEmpleado extends User{

//    @NotBlank(message = "vencimiento is mandatory")
    private LocalDate vencimientoCarne;

    private Long saldo;
    private Empresa empresaEmpl;

    private List<Servicio> serviciosFavoritos;

    private List<Reserva> reservasHechas;

    private List<Ingreso> ingresos;

    //CONSTRUCTORES


    public UserEmpleado(String nombre, String email, Long telefono, Long cedula, LocalDate vencimientoCarne, Long saldo, Empresa empresaEmpl) {
        super(nombre, email, telefono, cedula);
        this.vencimientoCarne = vencimientoCarne;
        this.saldo = saldo;
        this.empresaEmpl = empresaEmpl;
    }

    public UserEmpleado(String email) {
        super(email);
    }

    public UserEmpleado() {
    }

    //GETTERS Y SETTERS
    public LocalDate getVencimientoCarne() {
        return vencimientoCarne;
    }

    public void setVencimientoCarne(LocalDate vencimientoCarne) {
        this.vencimientoCarne = vencimientoCarne;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Empresa getEmpresaEmpl() {
        return empresaEmpl;
    }

    public void setEmpresaEmpl(Empresa empresaEmpl) {
        this.empresaEmpl = empresaEmpl;
    }

    public List<Servicio> getServiciosFavoritos() {
        return serviciosFavoritos;
    }

    public void setServiciosFavoritos(List<Servicio> serviciosFavoritos) {
        this.serviciosFavoritos = serviciosFavoritos;
    }

    public List<Reserva> getReservasHechas() {
        return reservasHechas;
    }

    public void setReservasHechas(List<Reserva> reservasHechas) {
        this.reservasHechas = reservasHechas;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }
}
