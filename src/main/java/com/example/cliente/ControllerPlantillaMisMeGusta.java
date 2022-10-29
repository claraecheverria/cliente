package com.example.cliente;

import com.example.cliente.Model.Servicio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

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

    public void setData(Servicio servicio){
//        javafx.scene.image.Image image = parseByteToImage(servicio.imagen)
//        Image.setImage(image);
//        setServicioEste(servicio);//REPASR SI ESTA BIEN
        Nombre.setText(servicio.getKey().getNombre());
        Direccion.setText(servicio.getCentroDeportivoServicio().getDireccion());
        Descripcion.setText(servicio.getDescripcion());
        HorarioInicio.setText(servicio.getHoraInicio());
        HorarioFin.setText(servicio.getHoraFin());
    }
}
