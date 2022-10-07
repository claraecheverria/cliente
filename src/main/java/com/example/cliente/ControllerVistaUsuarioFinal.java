package com.example.cliente;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControllerVistaUsuarioFinal implements Initializable {

    private Stage stage;
    private Scene scence;

    @FXML
    private BarChart<String, Integer> chart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("Lunes", 2323));
        series1.getData(). add(new XYChart.Data("Martes" , 4333));
        series1.getData().add(new XYChart.Data("Miercoles" ,10000));
        series1.getData().add(new XYChart.Data("Jueves",35000));
        series1.getData().add(new XYChart.Data("Viernes",12000));

        chart.getData() . addAll(series1);
    }
}
