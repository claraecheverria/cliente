package com.example.cliente;

import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.Servicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ControllerPlantillaMisMeGusta {

    @FXML
    private Label HorarioInicio;
    @FXML
    private Label HorarioFin;
    @FXML
    private Label Direccion;
    @FXML
    private Label Descripcion;
    @FXML
    private Label Nombre;
    @FXML
    private Button Reservar;
    @FXML
    private Button BotonMeGusta;

    private ServicioDTO servicioEste;

    public void setServicioEste(ServicioDTO servicioEste) {
        this.servicioEste = servicioEste;
    }

    public void setData(ServicioDTO servicio){
//        javafx.scene.image.Image image = parseByteToImage(servicio.imagen)
//        Image.setImage(image);
//        setServicioEste(servicio);//REPASR SI ESTA BIEN
        setServicioEste(servicio);
        Nombre.setText(servicio.getNombreServicio());
        Direccion.setText(servicio.getDireccion());
        Descripcion.setText(servicio.getDescripcion());
        HorarioInicio.setText(servicio.getHoraInicio().toString());
        HorarioFin.setText(servicio.getHoraFin().toString());
    }

    public void Reservar(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(ControllerPlantillaServicio.class.getResourceAsStream("SeleccionFechaReserva.fxml"));
        ((ControllerSeleccionFechaReserva)fxmlLoader.getController()).setServicio(servicioEste);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
