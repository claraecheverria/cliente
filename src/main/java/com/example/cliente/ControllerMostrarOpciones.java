package com.example.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerMostrarOpciones {

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


    public void switchToPaginaInicio(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("Page1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToConfiguracion(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("UsuarioFinalConfiguracion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void applyFillters(javafx.event.ActionEvent event) throws IOException{
        // APLICAR FILTRO SOBRE LOS SERVICIOS

    }

}
