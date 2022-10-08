package com.example.cliente;

import com.example.cliente.Model.CentroDeportivo;
import com.example.cliente.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ControllerTableViewCentrosDeportivos implements Initializable {

    private Stage stage;
    private Scene scence;

    public Button btnActualizar;
    public TableColumn<TableView<User>, String> colNombres;
    public TableColumn<TableView<User>, Integer> colRut;
    public TableColumn<TableView<User>, LocalDate> colRazonSocial;
    public TableColumn<TableView<User>, Double> colDireccion;
    public TableView<CentroDeportivo> tableCentroDeportivo;
    private ObservableList<CentroDeportivo> centroDeportivos;

    public List<CentroDeportivo> getCentrosDeportivosList(){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/centroDeportivo/listaCentrosDep")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            List<CentroDeportivo> listCar = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<CentroDeportivo>>(){});
            System.out.println(listCar.size());
            return listCar;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void Volver(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List listaCentrosDep = getCentrosDeportivosList();
        centroDeportivos = FXCollections.observableArrayList(listaCentrosDep);
        this.colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//        this.colRut.setCellValueFactory(new PropertyValueFactory<>("RUT"));
//        this.colRazonSocial.setCellValueFactory(new PropertyValueFactory<>("Razon Social"));
//        this.colDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));

        this.tableCentroDeportivo.setItems(centroDeportivos);
    }

    public void actualizarTablaCentrosDep(javafx.event.ActionEvent actionEvent){
        List listaEmpleados = getCentrosDeportivosList();
        centroDeportivos = FXCollections.observableArrayList(listaEmpleados);
        this.colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        this.tableCentroDeportivo.setItems(centroDeportivos);
    }
}
