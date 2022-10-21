package com.example.cliente;

import com.example.cliente.Model.Servicio;
import com.example.cliente.Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ControllerIngresarCliente implements Initializable {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Email;
    @FXML
    private ChoiceBox Servicios;
    @FXML
    private Button Button;
    private ObservableList<String> listaServicios;
    private List<Servicio> listaServiciosO = ListaServicios();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList listaNombreServicios = new ArrayList();
        for (int i = 0; i < listaServiciosO.size();i++){
            listaNombreServicios.add(listaServiciosO.get(i).getNombre());
        }

        listaServicios = (ObservableList<String>) listaNombreServicios;
        Servicios.setValue(listaServicios);
    }

    public void Ingresar(javafx.event.ActionEvent actionEvent){
        String email = Email.toString();
        String nombreSercicio = Servicios.toString();

        ArrayList arrayList = new ArrayList<>();


        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/agregarServicioFav") // HAY QUE DEFINIR LA HTTP BIEN
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(arrayList)
                .asJson();

    }

    public List<Servicio> ListaServicios(){    // hay que hacer la consulta a la base de datos

        List lista = new ArrayList();
        return lista;
    }


}
