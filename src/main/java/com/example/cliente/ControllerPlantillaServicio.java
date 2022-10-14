package com.example.cliente;

import com.example.cliente.Model.Servicio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ControllerPlantillaServicio {

    @FXML
    private Label Direccion;
    @FXML
    private Label Nombre;
    @FXML
    private Label Precio;
    @FXML
    private Button Reservar;
    @FXML
    private ImageView Image;

    private ArrayList listaServicios; // que sen objeto servicio

    public void setData(Servicio servicio){
//        Image image = new Image(getClass().getResourceAsStream(servicio.getImageScr()));
//        Image.setImage(image);
        Nombre.setText(servicio.getNombre());
        Direccion.setText(servicio.getCentroDeportivoServicio().getDireccion());
        Precio.setText(String.valueOf(servicio.getPrecio()));
    }
}
