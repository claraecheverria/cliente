package com.example.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerPlantillaHorarioReserva {

    @FXML
    private Button Boton;
    @FXML
    private AnchorPane AnchorPain;

    private boolean isSelected = false;

    public void setData(String horario){
        Boton.setText(horario);
    }

    public void seleccionar(javafx.event.ActionEvent actionEvent){
        if(isSelected == false){
            isSelected = true;
            Boton.setStyle("-fx-background-color: #1b68b3;");
            AnchorPain.setStyle("-fx-background-color: transparent");
        }
        else{
            isSelected = false;
            Boton.setStyle("-fx-background-color: #c1c1c1;");
        }

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
