package com.example.cliente;

import com.example.cliente.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerCrearEmpleado {

    private Stage stage;
    private Scene scence;
    @FXML
    private TextField Cedula;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Telefono;
    @FXML
    private TextField Email;
    @FXML
    private TextField FechaVencimiento;
    @FXML
    private TextField Importe;

    public void switchToAdminEmpresa(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdminEmpresa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void guardarDatos(javafx.event.ActionEvent event) throws IOException{

        Long cedula = Long.valueOf(Cedula.getText());
        String nombre = Nombre.toString();
        Long telefono = Long.valueOf(Telefono.getText());
        String email = Email.toString();
        String fechaVencimiento = FechaVencimiento.toString();
        int importe = Integer.parseInt(String.valueOf(Importe));

        Cedula.clear();
        Nombre.clear();
        Telefono.clear();
        Email.clear();
        FechaVencimiento.clear();
        Importe.clear();

        User nuevoUser = new User(nombre, email, telefono, cedula);


    }
}
