package com.example.cliente.Model;


import java.util.List;

public class CentroDeportivo {
    private String nombre;
    private String rut;
    private String razonSocial;
    private String direccion;

    private List<UserCentroDeportivo> usersCentroDep;
    private List<Servicio> serviciosCentroDep;

    //CONSTRUCTORES
    public CentroDeportivo() {
    }

    public CentroDeportivo(String nombre, String rut, String razonSocial, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

//    public List<UserCentroDeportivo> getUsersCentroDep() {
//        return usersCentroDep;
//    }
//
//    public void setUsersCentroDep(List<UserCentroDeportivo> usersCentroDep) {
//        this.usersCentroDep = usersCentroDep;
//    }
//
//    public List<Servicio> getServiciosCentroDep() {
//        return serviciosCentroDep;
//    }
//
//    public void setServiciosCentroDep(List<Servicio> serviciosCentroDep) {
//        this.serviciosCentroDep = serviciosCentroDep;
//    }

    @Override
    public String toString() {
        return "CentroDeportivo{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", direccion='" + direccion + '\'' +
                ", usersCentroDep=" + usersCentroDep +
                ", serviciosCentroDep=" + serviciosCentroDep +
                '}';
    }
}
