package com.example.cliente.Model;

public class UserCentroDeportivo extends User{


    private CentroDeportivo centroDeportivo;

    public UserCentroDeportivo(String nombre, String email, Long telefono, String password, Long cedula, CentroDeportivo centroDeportivo) {
        super(nombre, email, telefono, password, cedula);
        this.centroDeportivo = centroDeportivo;
    }

    public UserCentroDeportivo() {
    }

    public CentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
