package com.example.cliente.Model;


import java.util.List;



public class Servicio {

    private ServicioIdNew key;

    private CentroDeportivo centroDeportivoServicio;
    private Long precio;
    private String horario;
    private String descripcion;
    private String tipo; //este va a tener una opcion para seleccionar cuando se cree para hacer luego los filtros por tipo

    private List<UserEmpleado> favoritos;
//    @Lob
//    private byte[] imagen;


    //CONSTRUCTORES

    public Servicio() {
    }

    public Servicio(String name, CentroDeportivo centroDeportivoServicio, Long precio, String horario, String descripcion, String tipo) {
        this.key = new ServicioIdNew();
        this.key.setNombre(name);
        this.key.setCentroDeportivo(centroDeportivoServicio.getNombre());
        this.centroDeportivoServicio = centroDeportivoServicio;
        this.precio = precio;
        this.horario = horario;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    //GETTERS Y SETTERS


    public ServicioIdNew getKey() {
        return key;
    }

    public void setKey(ServicioIdNew key) {
        this.key = key;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//    public byte[] getImagen() {
//        return imagen;
//    }
//
//    public void setImagen(byte[] imagen) {
//        this.imagen = imagen;
//    }
//    @JsonSetter("imagen")
//    public void setImagen(String imagen) throws UnsupportedEncodingException {
//        this.imagen = Base64.decode(imagen.getBytes("UTF-8"));
//    }


    public CentroDeportivo getCentroDeportivoServicio() {
        return centroDeportivoServicio;
    }

    public void setCentroDeportivoServicio(CentroDeportivo centroDeportivoServicio) {
        this.centroDeportivoServicio = centroDeportivoServicio;
    }

}
