package com.example.cliente;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
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
        JSONObject jsonnn = new JSONObject();
        jsonnn.put("nombre", nombre);
        jsonnn.put("email", email);
        jsonnn.put("telefono", tel);
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
//                .body("{\"nombre\": \"" + nombre + "\", \"email\": \"" + email+ "\", \"telefono\": \"" + tel + "}")
                .body(jsonnn)
                .asJson();

        if (response.getStatus() != 200) {
            System.out.println("Failed to list Issues: " + response.getStatusText());
            System.out.println(response.getBody());
//            System.out.println(response.getBody().getObject());
        }
    }
}

