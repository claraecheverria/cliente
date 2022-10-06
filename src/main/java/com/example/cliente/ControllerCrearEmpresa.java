package com.example.cliente;

import com.example.cliente.Model.Empresa;
import com.example.cliente.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField cedula;
    @FXML
    private TextField nombreUsuario;
    @FXML
    private TextField telefono;
    @FXML
    private TextField email;
    @FXML
    private TextField fechaVencimientoCarne;
    @FXML
    private TextField importe;
    @FXML
    private Button guardar;

    @FXML
    private Label errorDatos;


    public void Volver(javafx.event.ActionEvent event) throws IOException {
        guardarDatos();

        if (check == true){
            try {
                switchToAdmin(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            errorDatos.setText("Algun dato es inconcistente");
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
        String nombre_empresa = nombreEmpresa.toString();
        Long cedula_usuario = Long.valueOf(cedula.getText());
        String nombre_usuario = nombreUsuario.toString();
        Long telefono_usuario = Long.valueOf(telefono.getText());
        String email_usuario = email.toString();
        String fecha_vencimiento_carne = fechaVencimientoCarne.toString();
        int importe_usuario = Integer.parseInt(String.valueOf(importe));

        nombreEmpresa.clear();
        cedula.clear();
        nombreUsuario.clear();
        telefono.clear();
        email.clear();
        fechaVencimientoCarne.clear();
        importe.clear();

        User nuevoUser = new User(nombre_usuario, email_usuario, telefono_usuario, cedula_usuario, fecha_vencimiento_carne, importe_usuario);

        Empresa nuevaEmpresa = new Empresa(nombre_empresa);

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/empresa/crearUserEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoUser)
                .asJson();
        System.out.println(response.getBody());
        if (response.getBody() != null){
            check = false;
        }
        System.out.println(response.getStatusText());

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/empresa/crearUserEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevaEmpresa)
                .asJson();
        System.out.println(response.getBody());
        if (response.getBody() != null){
            check = false;
        }
    }
}
