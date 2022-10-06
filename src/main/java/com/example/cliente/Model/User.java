package com.example.cliente.Model;


public class User {

//    private Long id;
    private String nombre;
    private String email;
    private Long telefono;
    private String password;
    private Long cedula;

    private String fechaVencimientoCane;
    private int importe;




    public User(String nombre, String email, Long telefono, Long cedula, String fechaVencimientoCane, int importe) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = "12345678";
        this.cedula = cedula;
        this.fechaVencimientoCane = fechaVencimientoCane;
        this.importe = importe;
    }

    public User (String email, String password){
        this.email = email;
        this.password = password;
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
