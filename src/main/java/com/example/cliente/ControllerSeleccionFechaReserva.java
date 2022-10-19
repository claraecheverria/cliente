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
import java.time.LocalDate;

@Controller
public class ControllerSeleccionFechaReserva {
    @FXML
    private DatePicker Calendario;
    @FXML
    private Button ConsultarFecha;

    private LocalDate fecha;

    public void consultarFecha(javafx.event.ActionEvent actionEvent) throws IOException {
        fecha = Calendario.getValue();

        FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setControllerFactory(Main.get()::getBeam);

        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("SeleccionarHorariosReserva.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

    }

    public LocalDate mandarFecha(){
        return fecha;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public DatePicker getCalendario() {
        return Calendario;
    }
}
