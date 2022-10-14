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
    ObservableList<String> tipoList = FXCollections.observableArrayList("GYM", "Futbol", "Pisina", "Yoga", "Spinning", "Tennis");
    @FXML
    private TextField Nombre;
    @FXML
    private ChoiceBox choiceBoxTipo;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Horarios;
    @FXML
    private TextField Cupos;

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
        int cupos = Integer.parseInt(String.valueOf(Cupos));

        Nombre.clear();
//        choiceBoxTipo.clear(); no esxiste clear pero hay que ver si cuando cargas otro servicio mantiene el valor anterior
        Precio.clear();
        Horarios.clear();
        Cupos.clear();

        Servicio nuevoServicio = new Servicio();//FIXME

        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/empresa/crearEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoServicio)
                .asJson();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxTipo.setValue(tipoList);
    }
}
