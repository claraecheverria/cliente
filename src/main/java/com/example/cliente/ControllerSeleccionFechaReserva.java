package com.example.cliente;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerSeleccionFechaReserva {

    @FXML
    private DatePicker Calendario;
    @FXML
    private Button ConsultarFecha;

    public void consultarFecha(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setControllerFactory(Main.get()::getBeam);

        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("SeleccionarHorariosReserva.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

    }

}
