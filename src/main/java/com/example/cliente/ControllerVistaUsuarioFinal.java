package com.example.cliente;

import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.Servicio;
import com.example.cliente.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ControllerVistaUsuarioFinal implements Initializable {

    private Stage stage;
    private Scene scence;

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private Button BotonConfiguracion;
    @FXML
    private Button botonMostrarOpciones;
    @FXML
    private HBox HboxMeGusta;
    @FXML
    private HBox HboxMeGusta1;
    @FXML
    private HBox HboxMeGusta2;
    @FXML
    private Label FechaDeHoy;
    @FXML
    private HBox misMeGusta;
    @FXML
    private Label Importe;

    private ArrayList<Servicio> ultimosServiciosUtilizados = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HttpResponse<String> response = Unirest.get("http://localhost:8080/user/saldo")
                .asString();
        Importe.setText(response.getBody());
        LocalDate date = LocalDate.now();
        FechaDeHoy.setText(date.toString());

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("Lunes", 80));
        series1.getData(). add(new XYChart.Data("Martes" , 60));
        series1.getData().add(new XYChart.Data("Miercoles" ,45));
        series1.getData().add(new XYChart.Data("Jueves",100));
        series1.getData().add(new XYChart.Data("Viernes",50));
        //chart.getData().addAll(series1);

        List<ServicioDTO> listaservicios = getListaServicios();

        List<ServicioDTO> listaMeGusta = getListaServiciosFav();
        try{
            for(int i = 0; i<listaMeGusta.size() ; i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PlantillaMeGusta.fxml"));
                AnchorPane serviceBox =  fxmlLoader.load();
                ControllerPlantillaMisMeGusta servicioController = fxmlLoader.getController();
                servicioController.setData(listaMeGusta.get(i));
                misMeGusta.getChildren().add(serviceBox);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            for(int i =0; i<2;i++){
                if(listaMeGusta != null && listaMeGusta.size() > 1) {
                    if(i == 0){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("PlantillaServicio.fxml"));
                        HBox serviceBox = null;
                        serviceBox = fxmlLoader.load();
                        ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                        servicioController.setData(listaMeGusta.get(i));
                        HboxMeGusta1.getChildren().add(serviceBox);
                    }
                    else{
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("PlantillaServicio.fxml"));
                        HBox serviceBox = null;
                        serviceBox = fxmlLoader.load();
                        ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                        servicioController.setData(listaMeGusta.get(i));
                        HboxMeGusta2.getChildren().add(serviceBox);
                    }

                }
                else{
                    if(i == 0){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("PlantillaServicioSinReserva.fxml"));
                        HBox serviceBox = null;
                        serviceBox = fxmlLoader.load();
                        ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                        servicioController.setData(listaservicios.get(i));
                        HboxMeGusta1.getChildren().add(serviceBox);
                    }
                    else{
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("PlantillaServicioSinReserva.fxml"));
                        HBox serviceBox = null;
                        serviceBox = fxmlLoader.load();
                        ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                        servicioController.setData(listaservicios.get(i));
                        HboxMeGusta2.getChildren().add(serviceBox);
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        // PARA QUE SE MUESTREN LOS DOS ULTIMOS SERVICIOS UTILIZADOS

        // listaUltimosServiciosUtilizados = ultimosServiciosUtilizados(Usuario)
//        try {
//            for (int i = 0; i < 2; i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                if(ultimosServiciosUtilizados.get(i).getTipo() == "clases"){  // HAY QUE DEFINIR BIEN COMO SON LOS TIPOS
//                    fxmlLoader.setLocation(getClass().getResource("plantillaServicio.fxml"));
//                }
//                else{
//                    fxmlLoader.setLocation(getClass().getResource("plantillaServicioSinReserva.fxml"));
//                }
//                HBox serviceBox = fxmlLoader.load();
//                serviceBox.setStyle("-fx-background-color: linear-gradient(to right, #1A3384,#2B49B3);-fx-background-radius: 10px;");
//                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
//                servicioController.setData(ultimosServiciosUtilizados.get(i));
//                HboxMeGusta.getChildren().add(serviceBox);
//            }
//        }
//        catch(IOException e){
//            throw new RuntimeException(e);
//        }

//        try{
//            for(int i = 0; i<listaServiciosFav.size();i++){
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("PlantillaMeGusta.fxml"));
//                HBox MeGustaBox = fxmlLoader.load();
//                ControllerPlantillaMisMeGusta misMeGustaController = fxmlLoader.getController();
//                misMeGustaController.setData(listaServiciosFav.get(i));
//                misMeGusta.getChildren().add(MeGustaBox);

//            }
//        }
//        catch ( IOException e){
//            throw new RuntimeException(e);
//        }

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

    public void switchToMostrarOpciones(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("MostrarOpciones2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToPaginaInicio(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("Page1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.show();
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
}
