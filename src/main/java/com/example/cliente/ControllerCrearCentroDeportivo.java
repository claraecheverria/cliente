package com.example.cliente;

import com.example.cliente.Model.CentroDeportivo;
import com.example.cliente.Model.Empresa;
import com.example.cliente.Model.UserCentroDeportivo;
import com.example.cliente.Model.UserEmpresa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerCrearCentroDeportivo {

    private Stage stage;
    private Scene scence;

    @FXML
    private TextField NombreCentroDeportivo;
    @FXML
    private TextField RazonSocial;
    @FXML
    private TextField RUT;
    @FXML
    private TextField Direccion;
    @FXML
    private TextField Cedula;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Telefono;
    @FXML
    private TextField Email;

    public void switchToAdmin(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }
    public void Volver(javafx.event.ActionEvent event) throws IOException {
        guardarDatos();
        try {
            switchToAdmin(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarDatos() {
        String nombre_centrodep = NombreCentroDeportivo.getText();
        String razonSocial = RazonSocial.getText();
        String rut = RUT.getText();
        String direccion = Direccion.getText();
        Long cedula_usuario = Long.valueOf(Cedula.getText());
        String nombre_usuario = Nombre.getText();
        Long telefono_usuario = Long.valueOf(Telefono.getText());
        String email_usuario = Email.getText();

        NombreCentroDeportivo.clear();
        RazonSocial.clear();
        RUT.clear();
        Direccion.clear();
        Cedula.clear();
        Nombre.clear();
        Telefono.clear();
        Email.clear();

        CentroDeportivo centroDeportivo = new CentroDeportivo(nombre_centrodep, rut, razonSocial, direccion);
        UserCentroDeportivo nuevoUserCentroDep = new UserCentroDeportivo(nombre_usuario,email_usuario,telefono_usuario,cedula_usuario,centroDeportivo);

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/centroDeportivo/crearCentroDep")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(centroDeportivo)
                .asJson();

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/centroDeportivo/crearUserCentroDep")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoUserCentroDep)
                .asJson();
    }

}
