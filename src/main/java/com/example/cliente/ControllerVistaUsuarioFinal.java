package com.example.cliente;

import com.example.cliente.Model.Servicio;
import com.example.cliente.Model.User;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private Label FechaDeHoy;
    private ArrayList<Servicio> ultimosServiciosUtilizados = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SETEAR LA FECHA DE HOY
        LocalDate date = LocalDate.now();
        FechaDeHoy.setText(date.toString());
        //

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("Lunes", 80));
        series1.getData(). add(new XYChart.Data("Martes" , 60));
        series1.getData().add(new XYChart.Data("Miercoles" ,45));
        series1.getData().add(new XYChart.Data("Jueves",100));
        series1.getData().add(new XYChart.Data("Viernes",50));
        //chart.getData().addAll(series1);

        // PARA QUE SE MUESTREN LOS DOS ULTIMOS SERVICIOS UTILIZADOS

        // listaUltimosServiciosUtilizados = ultimosServiciosUtilizados(Usuario)
        try {
            for (int i = 0; i < ultimosServiciosUtilizados.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                if(ultimosServiciosUtilizados.get(i).getTipo() == "clases"){  // HAY QUE DEFINIR BIEN COMO SON LOS TIPOS
                    fxmlLoader.setLocation(getClass().getResource("plantillaServicio.fxml"));
                }
                else{
                    fxmlLoader.setLocation(getClass().getResource("plantillaServicioSinReserva.fxml"));
                }
                HBox serviceBox = fxmlLoader.load();
                ControllerPlantillaServicio servicioController = fxmlLoader.getController();
                servicioController.setData(ultimosServiciosUtilizados.get(i));
                HboxMeGusta.getChildren().add(serviceBox);
            }
        }
        catch(IOException e){
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
    public void ulrimosServiciosUtilizados(User usuario){


    }
}
