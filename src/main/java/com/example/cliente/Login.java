package com.example.cliente;

import com.example.cliente.Model.Empresa;
import com.example.cliente.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private TextField Email;
    @FXML
    private TextField Password;

    private boolean check;

    private void checklogin() {
        check = true;
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        Email.clear();
            User nuevoUser = new User(email,password);
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

    public void userLogin(javafx.event.ActionEvent actionEvent) {
//        checklogin();
//        if (check == true) {
            try {
//                ScenceController.switchToAdmin(actionEvent);
                ScenceController.switchToClienteFinal(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        }else {
//            wrongLogin.setText("Login incorrecto");
//        }
    }
}

