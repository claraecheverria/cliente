package com.example.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
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
        Long tel = Long.valueOf(Telefono.getText());

//        User nuevoUser = new User(nombre, email, tel);
        Email.clear();
        Nombre.clear();
        Telefono.clear();
    }
}

