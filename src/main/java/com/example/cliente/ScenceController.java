package com.example.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
@Controller
public class ScenceController {
    private Stage stage;
    private Scene scence;
    private Parent root;

    private Text NombreUsuario;

    @FXML
    private BarChart<String, Integer> chart;

    public void switchToScence1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("LoginPage2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void switchToScence2(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("Page1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("StilePage1.css").toExternalForm();
        scence = new Scene(root);
        scence.getStylesheets().add(css);
        stage.setScene(scence);
        stage.initStyle(StageStyle.UNDECORATED);
        XYChart.Series<String,Integer> series_01 = new XYChart.Series<>();
        series_01.getData().add(new XYChart.Data<>("Mon", 60));
        series_01.getData().add(new XYChart.Data<>("Tue", 40));
        series_01.getData().add(new XYChart.Data<>("Wen", 80));
        series_01.getData().add(new XYChart.Data<>("Thr", 100));
        chart.getData().add(series_01);
        stage.show();

    }

}
