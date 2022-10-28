package com.example.cliente;

import ch.qos.logback.core.util.FileUtil;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static jdk.jfr.consumer.EventStream.openFile;

@Controller
public class ScenceController {
    private Stage stage;
    private Scene scence;
    private Parent root;
    private Text NombreUsuario;


    public void switchToScence1(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("LoginPage2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToClienteFinal(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("Page1V2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.show();
    }
    public void switchToAdmin(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToAdminEmpresa(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdminEmpresa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToAdminCentroDeporivo(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimeraVistaAdminCentroDeportivo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
        stage.centerOnScreen();
//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) /2);
//        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) /2);
    }

    public void pruebaSubirImagen(javafx.event.ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
//        fileChooser.showOpenDialog(stage);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("tengo file!!!");
        }
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/centroDeportivo/guardarFoto")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(encodedString)
                    .asJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }





}
