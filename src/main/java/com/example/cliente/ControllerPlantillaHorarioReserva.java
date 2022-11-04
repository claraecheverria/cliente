package com.example.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerPlantillaHorarioReserva {

    @FXML
    private Button Boton;
    @FXML
    private AnchorPane AnchorPain;
    @FXML
    private CheckBox Boton2;

    private boolean isSelected = false;

    public void setData(String horario){
        Boton2.setText(horario);
    }

    public void seleccionar(javafx.event.ActionEvent actionEvent){
        Boton.setStyle("-fx-background-color:#2B49B3;");
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
