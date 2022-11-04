package com.example.cliente;

import com.example.cliente.Model.Servicio;
import com.example.cliente.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox horaInicio;
    @FXML
    private ComboBox horaFin;
    private ObservableList<String> listaServicios;
    private List<Servicio> listaServiciosO = ListaServicios();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList listaNombreServicios = null;
        for (int i = 0; i < listaServiciosO.size();i++){
            listaNombreServicios.add(listaServiciosO.get(i).getKey().getNombre());
        }

        listaServicios =  listaNombreServicios;
        Servicios.setValue(listaServicios);
    }

    public void desplegarHorarios(){
        ObservableList listaHorariosDisponibles = null;

        if(Servicios.toString() == ""){  // REVISAR QUE PASA, SI ES ASI O CON NULL
            horaInicio.setValue(listaHorariosDisponibles);
            horaFin.setValue(listaHorariosDisponibles);
        }
        else{
            String nombreServicio = Servicios.toString();
            Servicio servico = null;

            for(int i = 0; i<listaServiciosO.size(); i++){
                if(listaServiciosO.get(i).getKey().getNombre() == nombreServicio){
                    servico = listaServiciosO.get(i);
                }
            }

            for(int i = Integer.parseInt(servico.getHoraInicio()); i <= Integer.parseInt(servico.getHoraFin()); i++ ){
                listaHorariosDisponibles.add(String.valueOf(i) + ":00");
            }
            horaInicio.setValue(listaHorariosDisponibles);
            horaFin.setValue(listaHorariosDisponibles);

        }

    }

    public void Ingresar(javafx.event.ActionEvent actionEvent){
        String email = Email.toString();
        String nombreSercicio = Servicios.toString();
        String horaInicial = horaInicio.toString();
        String horaFinal = horaFin.toString();

        Email.clear();

        ArrayList arrayList = new ArrayList<>();


        HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/agregarServicioFav?mail={mail}&nombreServico={nombreServicio}") // HAY QUE DEFINIR LA HTTP BIEN
                .routeParam("mail",email)
                .routeParam("nombreServicio",nombreSercicio)
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
