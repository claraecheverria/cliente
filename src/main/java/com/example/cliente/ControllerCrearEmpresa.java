package com.example.cliente;

import com.example.cliente.Model.Empresa;
import com.example.cliente.Model.User;
import com.example.cliente.Model.UserEmpresa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerCrearEmpresa {

    private Stage stage;
    private Scene scence;
    private Parent root;
    private boolean check;
    @FXML
    private TextField nombreEmpresa;
    @FXML
    private TextField RUT;
    @FXML
    private TextField RazonSocial;
    @FXML
    private TextField Direccion;
    @FXML
    private TextField cedula;
    @FXML
    private TextField nombreUsuario;
    @FXML
    private TextField telefono;
    @FXML
    private TextField email;
    @FXML
    private Button guardar;
    @FXML
    private PasswordField Contraseña;

    @FXML
    private Label errorDatos;


    public void Volver(javafx.event.ActionEvent event) throws IOException {
        guardarDatos();
        try {
            switchToAdmin(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToAdmin(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void guardarDatos() {
        String nombre_empresa = nombreEmpresa.getText();
        String rut = RUT.getText();
        String razonSocial = RazonSocial.getText();
        String direccion = Direccion.getText();
        Long cedula_usuario = Long.valueOf(cedula.getText());
        String nombre_usuario = nombreUsuario.getText();
        Long telefono_usuario = Long.valueOf(telefono.getText());
        String email_usuario = email.getText();
        String contraseña = Contraseña.getText();

        nombreEmpresa.clear();
        cedula.clear();
        nombreUsuario.clear();
        telefono.clear();
        email.clear();

        Empresa nuevaEmpresa = new Empresa(nombre_empresa, rut,razonSocial,direccion);
        UserEmpresa nuevoUserEmpresa = new UserEmpresa(nombre_usuario, email_usuario, telefono_usuario,contraseña, cedula_usuario, nuevaEmpresa);

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/empresa/crearEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevaEmpresa)
                .asJson();

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/empresa/crearUserEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoUserEmpresa)
                .asJson();

    }
}
