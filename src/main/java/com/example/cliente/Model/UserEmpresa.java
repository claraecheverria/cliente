package com.example.cliente.Model;

public class UserEmpresa extends User{

    private Empresa empresa;

    public UserEmpresa(String nombre, String email, Long telefono, String password, Long cedula, Empresa empresa) {
        super(nombre, email, telefono, password, cedula);
        this.empresa = empresa;
    }

    public UserEmpresa() {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
