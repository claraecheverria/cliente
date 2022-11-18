package com.example.cliente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public class ControllerAdmin {
    private Stage stage;
    private Scene scence;
    private Parent root;

    public void switchToCreatCentroDeportivo(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("CrearCentroDeportivo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void verEmpresasCreadas(javafx.event.ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("VerEmpresasCreadas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToCreatEmpresa(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("CrearEmpresa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void verCentrosDeportivosCreados(javafx.event.ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("VerCentrosDeportivosCreados.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void cerrarSecion(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("LoginPage2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.show();
    }

}
