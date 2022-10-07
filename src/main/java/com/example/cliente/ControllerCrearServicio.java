package com.example.cliente;

import com.example.cliente.Model.Servicio;
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
public class ControllerCrearServicio {

    private Stage stage;
    private Scene scence;

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Tipo;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Horarios;
    @FXML
    private TextField Cupos;

    public void switchToAdmin(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void guradarDatos(){
        String nombre = Nombre.toString();
        String tipo = Tipo.toString();
        int precio = Integer.parseInt(String.valueOf(Precio));
        String horarios = Horarios.toString();
        int cupos = Integer.parseInt(String.valueOf(Cupos));

        Nombre.clear();
        Tipo.clear();
        Precio.clear();
        Horarios.clear();
        Cupos.clear();

        Servicio nuevoServicio = new Servicio(nombre, precio, horarios,cupos);

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/empresa/crearEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoServicio)
                .asJson();
    }



}
