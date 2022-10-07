package com.example.cliente.Model;

public class UserCentroDeportivo extends User{


    private CentroDeportivo centroDeportivo;

    public UserCentroDeportivo(String nombre, String email, Long telefono, Long cedula, CentroDeportivo centroDeportivo) {
        super(nombre, email, telefono, cedula);
        this.centroDeportivo = centroDeportivo;
    }

    public UserCentroDeportivo() {
    }
}
