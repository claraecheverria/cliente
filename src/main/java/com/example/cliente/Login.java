package com.example.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.io.IOException;

@Component
public class Login {
    @Autowired
    private ScenceController ScenceController;

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

    private boolean check;

    private void checklogin() {
        check = true;
        String email = Email.getText().toString();
        String nombre = Nombre.getText().toString();
        Long tel = Long.valueOf(Telefono.getText());
        Email.clear();
        Nombre.clear();
        Telefono.clear();
        int length = String.valueOf(tel).length();
        System.out.println(length);
//        if (email.equals("") || nombre.equals("") || length < 9){
//            System.out.println("Errorrr!!");
//            check = false;
//        }else {
            User nuevoUser = new User(nombre, email, tel);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(nuevoUser)
                    .asJson();
            System.out.println(response.getBody());
            System.out.println(response.getStatusText());

//        if (response.getStatus() != 200) {
//            System.out.println("Failed to list Issues: " + response.getStatusText());
//            System.out.println(response.getBody());
//        }
//        }
    }

    public void userLogin(javafx.event.ActionEvent actionEvent) {
        checklogin();
        if (check == true) {
            try {
                ScenceController.switchToScence2(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            wrongLogin.setText("Login incorrecto");
        }
    }
}

