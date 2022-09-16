package com.example.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Login {

    public Login() {
        System.out.println("Constructor!!!!");
    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Email;
    @FXML
    private TextField Telefono;


    public void userLogin(ActionEvent event) throws IOException {
        checklogin();

    }

    private void checklogin() {
        String email = Email.getText().toString();
        String nombre = Nombre.getText().toString();
//        Long tel = Long.valueOf(Telefono.getText());
        String tel = Telefono.getText();
//        User nuevoUser = new User(nombre, email, tel);
        Email.clear();
        Nombre.clear();
        Telefono.clear();
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\"nombre\": \"" + nombre + "\", \"email\": \"" + email+ "\", \"telefono\": \"" + tel + "}")
                .asJson();

    }
}

