package com.example.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.IOException;

@Controller
public class ControllerCentroDeportivo {

    private Stage stage;
    private Scene scence;

    @FXML
    private Button AgregarServicio;
    @FXML
    private Button IngresoCliente;

    public void switchToCreatServicio(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("CrearServicios.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.show();
    }

    public void switchToIngresoCliente(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("AgregarEmpleadoEmpresa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.show();
    }



}