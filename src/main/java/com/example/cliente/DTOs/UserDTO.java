package com.example.cliente.DTOs;

public class UserDTO {
    private String email;
    private Long cedula;
    private String nombre;
    private String password;
    private Long telefono;

    //CONTRUCTORES


    public UserDTO() {
    }

    public UserDTO(String email, Long cedula, String nombre, String password, Long telefono) {
        this.email = email;
        this.cedula = cedula;
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
    }


    //GETTERS Y SETTERS

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
