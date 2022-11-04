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
    @FXML
    private Button Boton2;

    private boolean isSelected = false;

    public void setData(String horario){
        Boton2.setText(horario);
    }

    public void seleccionar(javafx.event.ActionEvent actionEvent){
        if(isSelected == false){
            isSelected = true;
//            Boton.getStyleClass().add("-fx-background-color: #1b68b3;");
            Boton2.getStyleClass().add("-fx-background-color: #1b68b3;");
//            AnchorPain.getStyleClass().add("-fx-background-color: transparent");
        }
        else{
            isSelected = false;
//            Boton.getStyleClass().add("-fx-background-color: #c1c1c1;");

            Boton2.getStyleClass().clear();
            Boton2.getStyleClass().add("-fx-background-color: #c1c1c1;");

//            AnchorPain.getStyleClass().clear();
        }

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
