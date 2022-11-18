package com.example.cliente;

import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.Servicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Controller
public class ControllerPlantillaMisMeGusta {

    @FXML
    private Label HorarioInicio;
    @FXML
    private Label HorarioFin;
    @FXML
    private Label Direccion;
    @FXML
    private Label Descripcion;
    @FXML
    private Label Nombre;
    @FXML
    private Button Reservar;
    @FXML
    private Button BottonMeGusta;

    private ServicioDTO servicioEste;

    private List<ServicioDTO> listaFav;

    private boolean estaPrecionado = false;

    public void setEstaPrecionado(boolean estaPrecionado) {
        this.estaPrecionado = estaPrecionado;
    }

    public void setServicioEste(ServicioDTO servicioEste) {
        this.servicioEste = servicioEste;
    }

    public void setData(ServicioDTO servicio){
        listaFav = getListaServiciosFav();
        setServicioEste(servicio);
        Nombre.setText(servicio.getNombreServicio());
        Direccion.setText(servicio.getDireccion());
        Descripcion.setText(servicio.getDescripcion());
        HorarioInicio.setText(servicio.getHoraInicio().toString());
        HorarioFin.setText(servicio.getHoraFin().toString());

        for(int i = 0; i < listaFav.size(); i++){
            if(Objects.equals(servicio.getNombreServicio(), listaFav.get(i).getNombreServicio()) && Objects.equals(servicio.getNombreCentroDep(), listaFav.get(i).getNombreCentroDep())){
                System.out.println("Entre pinta azul boton");
                BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");
            }
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
            System.out.println(listaServicios.size());
            return listaServicios;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void meGusta(javafx.event.ActionEvent actionEvent) {
        if (estaPrecionado == false) {
            BottonMeGusta.setStyle("-fx-background-color:#2B49B3;");
            System.out.println("apreté me gusta");
            System.out.println(servicioEste.getNombreServicio());
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
}
