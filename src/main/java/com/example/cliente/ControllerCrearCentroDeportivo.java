package com.example.cliente;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerCrearCentroDeportivo {

    private Stage stage;
    private Scene scence;

    @FXML
    private TextField NombreCentroDeportivo;
    @FXML
    private TextField Cedula;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Telefono;
    @FXML
    private TextField Email;
    @FXML
    private TextField FechaVencimientoCS;
    @FXML
    private TextField Importe;



}
