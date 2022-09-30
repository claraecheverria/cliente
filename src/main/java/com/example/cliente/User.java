package com.example.cliente;


public class User {

//    private Long id;
    private String nombre;
    private String email;
    private Long telefono;
    private String password;
    private Long cedula;



    public User(String nombre, String email, Long telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public User() {//jpa no te deja tener un constrctor con pasando cosas

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
