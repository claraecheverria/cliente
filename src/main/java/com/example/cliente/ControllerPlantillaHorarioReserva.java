package com.example.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerPlantillaHorarioReserva {

    @FXML
    private Button Boton;

    private boolean isSelected;

    public void setData(String horario){
        Boton.setText(horario);
    }

    public void seleccionar(javafx.event.ActionEvent actionEvent){
        isSelected = true;

        Boton.setStyle("-fx-background-color: #c1c1c1;");
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
