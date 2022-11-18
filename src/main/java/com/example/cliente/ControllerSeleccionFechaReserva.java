package com.example.cliente;

import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.Servicio;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class ControllerSeleccionFechaReserva {
    private Stage stage;
    private Scene scence;
    @FXML
    private DatePicker Calendario;
    @FXML
    private Button ConsultarFecha;

    private ServicioDTO servicio;

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    private LocalDate fecha;
    private ControllerPlantillaServicio controllerPlantillaServicio;


    public void consultarFecha(javafx.event.ActionEvent actionEvent) throws IOException {
        fecha = Calendario.getValue();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("SeleccionarHorariosReserva.fxml"));
        ((ControllerSeleccionarHorariosReserva) fxmlLoader.getController()).setServicio(servicio);
        ((ControllerSeleccionarHorariosReserva)fxmlLoader.getController()).setSeleccionFechaReserva(this);
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void volver(javafx.event.ActionEvent actionEvent){
        stage.close();
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

    public void setPlantillaServicio(ControllerPlantillaServicio controllerPlantillaServicio) {
        this.controllerPlantillaServicio = controllerPlantillaServicio;
    }
}
