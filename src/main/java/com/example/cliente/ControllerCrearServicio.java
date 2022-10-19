package com.example.cliente;

import com.example.cliente.Model.Servicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ControllerCrearServicio implements Initializable {

    private Stage stage;
    private Scene scence;
    ObservableList<String> tipoList = FXCollections.observableArrayList("GYM", "Futbol", "Pisina", "Yoga", "Spinning", "Tennis", "Spinning");
    ObservableList<String> horariosList = FXCollections.observableArrayList("0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00");
    @FXML
    private TextField Nombre;
    @FXML
    private ChoiceBox choiceBoxTipo;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Horarios;
    @FXML
    private ChoiceBox HorarioInicio;
    @FXML
    private ChoiceBox HorarioFin;
    @FXML
    private TextField Cupos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxTipo.setValue(tipoList);
        HorarioInicio.setValue(horariosList);
        HorarioFin.setValue(horariosList);
    }

    public void switchToAdmin(javafx.event.ActionEvent event) throws IOException {
        guradarDatos();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void guradarDatos(){
        String nombre = Nombre.toString();
        String tipo = choiceBoxTipo.toString();
        int precio = Integer.parseInt(String.valueOf(Precio));
        String horarios = Horarios.toString();
        String horarioInicio = HorarioInicio.toString();
        String horarioFin = HorarioFin.toString();
        int cupos = Integer.parseInt(String.valueOf(Cupos));

        Nombre.clear();
//        choiceBoxTipo.clear(); no esxiste clear pero hay que ver si cuando cargas otro servicio mantiene el valor anterior
//        HorarioFin.clear();
//        HorarioInicio.clear();
        Precio.clear();
        Horarios.clear();
        Cupos.clear();

        Servicio nuevoServicio = new Servicio();//FIXME

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/servicio/crearServicio")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoServicio)
                .asJson();
    }
}
