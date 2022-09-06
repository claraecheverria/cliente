package com.example.cliente;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClienteApplication {
    private static ConfigurableApplicationContext context;
    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
//        Application.launch(HelloApplication.class, args);
    }

}
