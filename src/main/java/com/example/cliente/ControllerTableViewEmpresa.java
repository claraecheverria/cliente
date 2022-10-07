package com.example.cliente;

import com.example.cliente.HelloApplication;
import com.example.cliente.Model.Empresa;
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
public class ControllerTableViewEmpresa implements Initializable {
    private Stage stage;
    private Scene scence;
    private Parent root;
    public Button btnActualizar;

    // COLUMNAS TABLA EMPLEADOS
    public TableColumn<TableView<User>, String> colCorreo;
    public TableColumn<TableView<User>, String> colNombres;
    public TableColumn<TableView<User>, Integer> colCedula;
    public TableColumn<TableView<User>, LocalDate> colVenCarne;
    public TableColumn<TableView<User>, Double> colSaldo;
    public TableView<User> tableEmpleado;
    private ObservableList<User> empleados;

    // COLUMNAS TABLA EMPRESAS
    public  TableColumn<TableView<User>, String> colNombreEmpresa;
    public TableView<Empresa> tablaEmpresasCreadas;
    private ObservableList<Empresa> empresas;
    public Button btnActualizarTablaEmpresas;
    public Button btnVolverAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List listaEmpresas = getUserList();
        empresas = FXCollections.observableArrayList(listaEmpresas);
        this.colNombreEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.tablaEmpresasCreadas.setItems(empresas);
    }

    public void actualizarTablaEmpresas(javafx.event.ActionEvent actionEvent){
        List listaEmpleados = getUserList();
        empresas = FXCollections.observableArrayList(listaEmpleados);
        this.colNombreEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        this.tablaEmpresasCreadas.setItems(empresas);
    }

    public void Volver(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }
    public List<Empresa> getUserList (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/empresa/listaempresas")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            List<Empresa> listCar = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Empresa>>(){});
            System.out.println(listCar.size());
            return listCar;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
