package com.example.cliente.DTOs;

import java.time.LocalDate;

public class UserEmpleadoDTO extends UserDTO{

    private LocalDate vencimientoCarne;

    private Long saldo;

    private String empresaEmpl;

    //CONTRUCTORES

    public UserEmpleadoDTO() {
    }

    public UserEmpleadoDTO(String email, Long cedula, String nombre, String password, Long telefono, LocalDate vencimientoCarne, Long saldo, String empresaEmpl) {
        super(email, cedula, nombre, password, telefono);
        this.vencimientoCarne = vencimientoCarne;
        this.saldo = saldo;
        this.empresaEmpl = empresaEmpl;
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

    public String getEmpresaEmpl() {
        return empresaEmpl;
    }

    public void setEmpresaEmpl(String empresaEmpl) {
        this.empresaEmpl = empresaEmpl;
    }
}
