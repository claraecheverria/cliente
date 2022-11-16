package com.example.cliente;

import com.example.cliente.DTOs.CanchaDTO;
import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.Cancha;
import com.example.cliente.Model.Servicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ControllerMostrarOpciones implements Initializable {

    private Stage stage;
    private Scene scence;
    //FILTROS
    @FXML
    private CheckBox FillterFutbol;
    @FXML
    private CheckBox FillterPiscina;
    @FXML
    private CheckBox FillterGym;
    @FXML
    private CheckBox FillterYoga;
    @FXML
    private CheckBox FillterTennis;
    @FXML
    private CheckBox FillterSpinning;
    @FXML
    private Button ApplyFillters;
    @FXML
    private CheckBox fillterPrimerRango; // 0 - 150
    @FXML
    private CheckBox fillterSegundoRango; // 150 - 300
    @FXML
    private CheckBox FillterTercerRango; // 300 - 450
    @FXML
    private Label Importe;

    private ArrayList<CheckBox> filltersList = new ArrayList();


    //COLUMNAS
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox Vbox;
    @FXML
    private VBox Vbox1;
    @FXML
    private VBox Vbox2;
    private List<ServicioDTO> listaservicios;
    private List<CanchaDTO> listaCanchas = getListaCanchas();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaservicios = getListaServicios();
        HttpResponse<String> response = Unirest.get("http://localhost:8080/user/saldo")
                .asString();
        Importe.setText(response.getBody());

        try {
            for (int i = 0; i < listaservicios.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("plantillaServicioSinReserva.fxml"));
                HBox serviceBox = fxmlLoader.load();
                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                servicioController.setData(listaservicios.get(i));
                if(i%2 == 0){
                    Vbox1.getChildren().add(serviceBox);
                }
                if(i%2 == 1){
                    Vbox2.getChildren().add(serviceBox);
                }
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            for (int i = 0; i < listaCanchas.size(); i++) {
//                System.out.println(listaCanchas.get(i).getKey().getNombre());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("plantillaServicio.fxml"));
                HBox serviceBox = fxmlLoader.load();
                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                servicioController.setData(listaCanchas.get(i));
                if(i%2 == 0){
                    Vbox1.getChildren().add(serviceBox);
                }
                if(i%2 == 1){
                    Vbox2.getChildren().add(serviceBox);
                }
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void applyFillters(javafx.event.ActionEvent event) throws IOException{
        ArrayList<ServicioDTO> listaServiciosSeleccionados = new ArrayList<>();
        ArrayList<CanchaDTO> listaServiciosSeleccionadosReserva = new ArrayList<>();
        Vbox1.getChildren().clear();
        Vbox2.getChildren().clear();

        if (fillterPrimerRango.isSelected()){
            for(int i = 0; i<listaservicios.size(); i++){
                if(listaservicios.get(i).getPrecio()<150){
                    listaServiciosSeleccionados.add(listaservicios.get(i));
                }
            }
            for(int j = 0; j<listaCanchas.size();j++){
                if(listaCanchas.get(j).getPrecio()<150){
                    listaServiciosSeleccionadosReserva.add(listaCanchas.get(j));
                }
            }
        }
        if (fillterSegundoRango.isSelected()){
            for(int i = 0; i<listaservicios.size(); i++){
                if(listaservicios.get(i).getPrecio()<=300 && listaservicios.get(i).getPrecio()>=150){
                    listaServiciosSeleccionados.add(listaservicios.get(i));
                }
            }
            for(int j = 0; j<listaCanchas.size();j++){
                if(listaCanchas.get(j).getPrecio()<=300 && listaCanchas.get(j).getPrecio()>=150){
                    listaServiciosSeleccionadosReserva.add(listaCanchas.get(j));
                }
            }
        }
        if (FillterTercerRango.isSelected()){
            for(int i = 0; i<listaservicios.size(); i++){
                if(listaservicios.get(i).getPrecio()<=450 && listaservicios.get(i).getPrecio()>=300){
                    listaServiciosSeleccionados.add(listaservicios.get(i));
                }
            }
            for(int j = 0; j<listaCanchas.size();j++){
                if(listaCanchas.get(j).getPrecio()<=450 && listaCanchas.get(j).getPrecio()>=300){
                    listaServiciosSeleccionadosReserva.add(listaCanchas.get(j));
                }
            }
        }
        if (!fillterPrimerRango.isSelected() && !fillterSegundoRango.isSelected() && !FillterTercerRango.isSelected()){
            for(int i = 0; i<listaservicios.size(); i++){
               listaServiciosSeleccionados.add(listaservicios.get(i));
            }
            for(int j = 0; j<listaCanchas.size();j++){
                    listaServiciosSeleccionadosReserva.add(listaCanchas.get(j));
            }
        }

        listaServiciosSeleccionados = removeDuplicates(listaServiciosSeleccionados);
        listaServiciosSeleccionadosReserva = removeDuplicates(listaServiciosSeleccionadosReserva);

        desplegarPlantillas(listaServiciosSeleccionados);
        desplegarPlantillasConReserva(listaServiciosSeleccionadosReserva);


    }

    public List<ServicioDTO> getListaServicios (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/listaServiciosDTO")
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

    public List<CanchaDTO> getListaCanchas (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/listaServiciosCanchaDTO")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.findAndRegisterModules();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(df);
            List<CanchaDTO> canchaDTOList = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<CanchaDTO>>(){});
            return canchaDTOList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToPaginaInicio(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("Page1V2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToConfiguracion(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("UsuarioFinalConfiguracion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.show();
    }

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }

        return newList;
    }

    public void desplegarPlantillas(ArrayList<ServicioDTO> listaParaDesplegar){
        try {
            for (int i = 0; i < listaParaDesplegar.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("plantillaServicioSinReserva.fxml"));
                HBox serviceBox = fxmlLoader.load();
                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                servicioController.setData(listaParaDesplegar.get(i));
                if(i%2 == 0){
                    Vbox1.getChildren().add(serviceBox);
                }
                if(i%2 == 1){
                    Vbox2.getChildren().add(serviceBox);
                }
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void desplegarPlantillasConReserva(ArrayList<CanchaDTO> listaParaDesplegar){
        try {
            for (int i = 0; i < listaParaDesplegar.size(); i++) {
//                System.out.println(listaCanchas.get(i).getKey().getNombre());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("plantillaServicio.fxml"));
                HBox serviceBox = fxmlLoader.load();
                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                servicioController.setDataCancha(listaParaDesplegar.get(i));
                if(i%2 == 0){
                    Vbox1.getChildren().add(serviceBox);
                }
                if(i%2 == 1){
                    Vbox2.getChildren().add(serviceBox);
                }
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}
