package com.example.cliente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerMostrarOpciones {

    private Stage stage;
    private Scene scence;

    public void switchToPaginaInicio(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("MostrarOpciones.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.show();
    }

    public void switchToConfiguracion(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("AgregarEmpleadoEmpresa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.show();
    }

}
