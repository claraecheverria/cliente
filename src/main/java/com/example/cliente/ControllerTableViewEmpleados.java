package com.example.cliente;

import com.example.cliente.DTOs.UserEmpleadoDTO;
import com.example.cliente.HelloApplication;
import com.example.cliente.Model.Empresa;
import com.example.cliente.Model.User;
import com.example.cliente.Model.UserEmpleado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
@Controller
public class ControllerTableViewEmpleados implements Initializable {

    private Stage stage;
    private Scene scence;
    private Parent root;
    public Button btnActualizar;

    public TableColumn<TableView<User>, String> colCorreo;
    public TableColumn<TableView<User>, String> colNombres;
    public TableColumn<TableView<User>, Integer> colCedula;
    public TableColumn<TableView<User>, LocalDate> colVenCarne;
    public TableColumn<TableView<User>, Double> colSaldo;
    public TableView<User> tableEmpleado;
    private ObservableList<User> empleados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List listaEmpleados = getUserList();
        empleados = FXCollections.observableArrayList(listaEmpleados);
        this.colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory<>("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        this.tableEmpleado.setItems(empleados);
    }

    public List<UserEmpleadoDTO> getUserList (){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/empresa/listaEmpleadosDeEmpresa")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.findAndRegisterModules();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(df);
            List<UserEmpleadoDTO> userEmpleadoDTOList = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<UserEmpleadoDTO>>(){});

            return userEmpleadoDTOList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void Volver(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimerVistaAdminEmpresa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
    }

    public void actualizarTablaEmpleados (javafx.event.ActionEvent actionEvent) {
        empleados = FXCollections.observableArrayList();
        this.colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory<>("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        this.tableEmpleado.setItems(empleados);
    }
}
