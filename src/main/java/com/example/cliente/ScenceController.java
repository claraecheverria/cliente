package com.example.cliente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ScenceController {

    private Stage stage;
    private Scene scence;
    private Parent root;

    private Text NombreUsuario;

    public void switchToScence1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("LoginPage2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();


    }

    public void switchToScence2(ActionEvent event){

    }

}
