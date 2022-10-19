package com.example.cliente;

import com.example.cliente.Model.Cancha;
import com.example.cliente.Model.CentroDeportivo;
import com.example.cliente.Model.Servicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
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
    private Servicio servicio;

    public void setData(Servicio servicio){
//        Image image = new Image(getClass().getResourceAsStream(servicio.getImageScr()));
//        Image.setImage(image);
        servicio = servicio;  //REPASR SI ESTA BIEN
        Nombre.setText(servicio.getKey().getNombre());
        Direccion.setText(servicio.getCentroDeportivoServicio().getDireccion());
        Precio.setText(String.valueOf(servicio.getPrecio()));
        Descripcion.setText(servicio.getDescripcion());
        int tam = servicio.getDias().size();
        String horarios = new String();
        for (int i = 0; i < tam; i++){
            horarios = horarios + servicio.getDias().toString();
        }
        Horarios.setText(horarios);
        centroDeportio = servicio.getCentroDeportivoServicio();

    }

    public void meGusta(javafx.event.ActionEvent actionEvent){
        BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/agregarServicioFav") // HAY QUE DEFINIR LA HTTP BIEN
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(servicio)
                .asJson();
    }

    public void sacarMeGusta(javafx.event.ActionEvent actionEvent){
        BottonMeGusta.setStyle("-fx-background-color: #C9C9C9;");

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/agregarServicioFav") // HAY QUE DEFINIR LA HTTP BIEN
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(servicio)
                .asJson();
    }

    public void Reservar(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setControllerFactory(HelloApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(ControllerPlantillaServicio.class.getResourceAsStream("SeleccionFechaReservar.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public Cancha devolverCancha(){
        return (Cancha) servicio;
    }

    public Servicio devolverServicio(){
        return servicio;
    }

    public CentroDeportivo devolverCentroDeportivo(){
        return centroDeportio;
    }


}
