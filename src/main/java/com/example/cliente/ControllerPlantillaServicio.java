package com.example.cliente;

import com.example.cliente.DTOs.CanchaDTO;
import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.*;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.embed.swing.SwingFXUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControllerPlantillaServicio {

    @FXML
    private Text Direccion;
    @FXML
    private Label Nombre;
    @FXML
    private Label Precio;
    @FXML
    private Button Reservar;
    @FXML
    private ImageView Image;
    @FXML
    private Text Descripcion;
    @FXML
    private Text Horarios;
    @FXML
    private Button BottonMeGusta;
    @FXML
    private Label Horario;
    private CentroDeportivo centroDeportio;
    private ServicioDTO servicioEste;
    private boolean estaPrecionado = false;

    private Stage stage;

    private List<ServicioDTO> listaFav;

    public CentroDeportivo getCentroDeportio() {
        return centroDeportio;
    }

    public void setCentroDeportio(CentroDeportivo centroDeportio) {
        this.centroDeportio = centroDeportio;
    }

    public ServicioDTO getServicioEste() {
        return servicioEste;
    }

    public void setServicioEste(ServicioDTO servicioEste) {
        this.servicioEste = servicioEste;
    }

    public void setData(ServicioDTO servicio){
        byte[] decodedBytes;
        listaFav = getListaServiciosFav();
        if(servicio.getImagenes() == null || servicio.getImagenes().size() == 0){

        }else{
            Imagen[] imagenes = servicio.getImagenes().toArray(new Imagen[servicio.getImagenes().size()]);
            decodedBytes = Base64.getDecoder().decode(imagenes[0].getImagen());
            ByteArrayInputStream i = new ByteArrayInputStream(decodedBytes);
            Image.setImage(new Image(i));

        }
        setServicioEste(servicio);
        Nombre.setText(servicio.getNombreServicio());
        Direccion.setText(servicio.getDireccion());
        Precio.setText(String.valueOf(servicio.getPrecio()));
        Descripcion.setText(servicio.getDescripcion());
        int tam = servicio.getDias().size();
        Object[] array = servicio.getDias().toArray();
        String dias = new String();
        for (int i = 0; i < tam; i++){
            dias = dias + array[i] + ", ";
        }
        Horarios.setText(dias);
        Horario.setText(servicio.getHoraInicio().toString() + " - " + servicio.getHoraFin().toString());

        for(int i = 0; i < listaFav.size(); i++){
            if(Objects.equals(servicio.getNombreServicio(), listaFav.get(i).getNombreServicio()) && Objects.equals(servicio.getNombreCentroDep(), listaFav.get(i).getNombreCentroDep())){
                BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");
                estaPrecionado = true;
            }
        }

    }
    public void setDataCancha(CanchaDTO cancha){
        byte[] decodedBytes;
        if(cancha.getImagenes().size() == 0){

        }else{
            Imagen[] imagenes = cancha.getImagenes().toArray(new Imagen[cancha.getImagenes().size()]);
            decodedBytes = Base64.getDecoder().decode(imagenes[0].getImagen());
            ByteArrayInputStream i = new ByteArrayInputStream(decodedBytes);
            Image.setImage(new Image(i));

        }
        setServicioEste(cancha);
        Nombre.setText(cancha.getNombreServicio());
        Direccion.setText(cancha.getDireccion());
        Precio.setText(String.valueOf(cancha.getPrecio()));
        Descripcion.setText(cancha.getDescripcion());
        int tam = cancha.getDias().size();
        Object[] array = cancha.getDias().toArray();
        String dias = new String();
        for (int i = 0; i < tam; i++){
            dias = dias + array[i] + ", ";
        }
        Horarios.setText(dias);
        Horario.setText(cancha.getHoraInicio().toString() + " - " + cancha.getHoraFin().toString());

        for(int i = 0; i < listaFav.size(); i++){
            if(Objects.equals(cancha.getNombreServicio(), listaFav.get(i).getNombreServicio()) && Objects.equals(cancha.getNombreCentroDep(), listaFav.get(i).getNombreCentroDep())){
                BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");
                estaPrecionado = true;
            }
        }

    }

    public void meGusta(javafx.event.ActionEvent actionEvent) {
        if (estaPrecionado == false) {
            BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");
            setEstaPrecionado(true);
            try {
                com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                objectMapper.findAndRegisterModules();
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                objectMapper.setDateFormat(df);
                String serialized = null;
                serialized = objectMapper.writeValueAsString(servicioEste);

                HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/agregarServicioFavDTO")
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(serialized)
                        .asJson();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            BottonMeGusta.setStyle("-fx-background-color: #FFFFFF;");
            try {
                com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                objectMapper.findAndRegisterModules();
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                objectMapper.setDateFormat(df);
                String serialized = null;
                serialized = objectMapper.writeValueAsString(servicioEste);

                HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/eliminarServicioFav")
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(serialized)
                        .asJson();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Reservar(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(ControllerPlantillaServicio.class.getResourceAsStream("SeleccionFechaReserva.fxml"));
        ((ControllerSeleccionFechaReserva)fxmlLoader.getController()).setServicio(servicioEste);
        ((ControllerSeleccionFechaReserva)fxmlLoader.getController()).setPlantillaServicio(this);
        stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void close(){
        stage.close();
    }


    public void bajarPagina(javafx.event.ActionEvent event){

    }
    public List<ServicioDTO> getListaServiciosFav (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/serviciosFavDeUnUserDTO")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.findAndRegisterModules();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(df);
            List<ServicioDTO> listaServicios = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<ServicioDTO>>(){});
            return listaServicios;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void Volver(javafx.event.ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    public void setEstaPrecionado(boolean estaPrecionado) {
        this.estaPrecionado = estaPrecionado;
    }



}
