package com.example.cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Parent root;

    @Override
    public void init() {

    }

    @Override
    public void stop() {
        ClienteApplication.getContext().close();
    }
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
        root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("com/example/cliente/LoginPage2.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}