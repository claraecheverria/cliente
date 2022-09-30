package com.example.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.List;

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
            User nuevoUser = new User(nombre, email, tel);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(nuevoUser)
                    .asJson();
            System.out.println(response.getBody());
            if (response.getBody() != null){
                check = false;
            }
            System.out.println(response.getStatusText());
    }
    public List<User> getUserList (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/listausers")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            List<User> listCar = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<User>>(){});
            System.out.println(listCar.size());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



        return (List<User>) response;
    }

    public void userLogin(javafx.event.ActionEvent actionEvent) {
//        checklogin();
        getUserList();
//        if (check == true) {
//            try {
//                ScenceController.switchToAdmin(actionEvent);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            wrongLogin.setText("Login incorrecto");
//        }
    }
}

