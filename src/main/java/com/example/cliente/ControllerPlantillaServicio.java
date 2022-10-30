package com.example.cliente;

import com.example.cliente.Model.Cancha;
import com.example.cliente.Model.CentroDeportivo;
import com.example.cliente.Model.Imagen;
import com.example.cliente.Model.Servicio;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class ControllerPlantillaServicio {

    @FXML
    private Label Direccion;
    @FXML
    private Label Nombre;
    @FXML
    private Label Precio;
    @FXML
    private Button Reservar;
    @FXML
    private ImageView Image;
    @FXML
    private Label Descripcion;
    @FXML
    private Label Horarios;
    @FXML
    private Button BottonMeGusta;
    private CentroDeportivo centroDeportio;
    private Servicio servicioEste;
    private boolean estaPrecionado;

    public CentroDeportivo getCentroDeportio() {
        return centroDeportio;
    }

    public void setCentroDeportio(CentroDeportivo centroDeportio) {
        this.centroDeportio = centroDeportio;
    }

    public Servicio getServicioEste() {
        return servicioEste;
    }

    public void setServicioEste(Servicio servicioEste) {
        this.servicioEste = servicioEste;
    }

    public void setData(Servicio servicio){
        byte[] decodedBytes;
        if(servicio.getImagenes().size() == 0){

        }else{
            Imagen[] imagenes = servicio.getImagenes().toArray(new Imagen[servicio.getImagenes().size()]);
            decodedBytes = Base64.getDecoder().decode(imagenes[0].getImagen());
            ByteArrayInputStream i = new ByteArrayInputStream(decodedBytes);
            Image.setImage(new Image(i));

        }
        setServicioEste(servicio);
        System.out.println(servicioEste.getKey().getNombre());
        Nombre.setText(servicio.getKey().getNombre());
        Direccion.setText(servicio.getCentroDeportivoServicio().getDireccion());
        Precio.setText(String.valueOf(servicio.getPrecio()));
        Descripcion.setText(servicio.getDescripcion());
        int tam = servicio.getDias().size();
        String horarios = new String();
//        for (int i = 0; i < tam; i++){
            horarios = horarios + servicio.getDias().toString();
//        }
        Horarios.setText(horarios);
//        BottonMeGusta.setStyle("-fx-background-color: #C9C9C9;");
        centroDeportio = servicio.getCentroDeportivoServicio();

    }

    public void meGusta(javafx.event.ActionEvent actionEvent) {
        if (estaPrecionado == false) {
            BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");
            System.out.println("apreté me gusta");
            System.out.println(servicioEste.getKey().getNombre());

            HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/agregarServicioFav")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(servicioEste)
                    .asJson();
        }
        else {
            BottonMeGusta.setStyle("-fx-background-color: #C9C9C9;");

            HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/eliminarServicioFav")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(servicioEste)
                    .asJson();
        }
    }

    public void Reservar(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(ControllerPlantillaServicio.class.getResourceAsStream("SeleccionFechaReserva.fxml"));
        ((ControllerSeleccionFechaReserva)fxmlLoader.getController()).setServicio(servicioEste);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void Volver(javafx.event.ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    public Cancha devolverCancha(){
        return (Cancha) servicioEste;
    }

    public Servicio devolverServicio(){
        return servicioEste;
    }

    public CentroDeportivo devolverCentroDeportivo(){
        return centroDeportio;
    }


}
