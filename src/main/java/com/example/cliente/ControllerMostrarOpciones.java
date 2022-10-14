package com.example.cliente;

import com.example.cliente.Model.Empresa;
import com.example.cliente.Model.Servicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ControllerMostrarOpciones implements Initializable {

    private Stage stage;
    private Scene scence;
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
    private Button ApplyFillters;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPain;
    private ArrayList<Servicio> listaservicios;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //listaservicios = lista de servicios disponibles
        listaservicios = (ArrayList<Servicio>) getListaServicios();
        try {
            for (int i = 0; i < listaservicios.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox serviceBox = fxmlLoader.load();
                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                servicioController.setData(listaservicios.get(i));
                anchorPain.getChildren().add(serviceBox);
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public List<Servicio> getListaServicios (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/listaServicios")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            List<Servicio> listaServicios = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Servicio>>(){});
            System.out.println(listaServicios.size());
            return listaServicios;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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

    public void applyFillters(javafx.event.ActionEvent event) throws IOException{
        // APLICAR FILTRO SOBRE LOS SERVICIOS

    }
}