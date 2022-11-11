package com.example.cliente;

import com.example.cliente.Model.Ingreso;
import com.example.cliente.Model.Servicio;
import com.example.cliente.Model.User;
import com.example.cliente.Model.UserEmpleado;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.example.cliente.Model.UserEmpleado;
import javafx.collections.FXCollections;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//@Controller
public class ControllerIngresarCliente implements Initializable {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Email;
    @FXML
    private ComboBox Servicios;
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
        ObservableList listaNombreServicios = FXCollections.observableArrayList();
        for (int i = 0; i < listaServiciosO.size();i++){
            listaNombreServicios.add(listaServiciosO.get(i).getKey().getNombre());
        }

        listaServicios =  listaNombreServicios;
        for(int i = 0; i < listaServicios.size(); i++){
            Servicios.getItems().add(listaServicios.get(i));
        }
//        Servicios.setValue(listaServicios);
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
//                listaHorariosDisponibles.add(String.valueOf(i) + ":00");
                horaInicio.getItems().add(i + ":00");
                horaFin.getItems().add(i + ":00");

            }

//            horaInicio.setValue(listaHorariosDisponibles);
//            horaFin.setValue(listaHorariosDisponibles);

        }

    }

    public void Ingresar(javafx.event.ActionEvent actionEvent){
        String email = Email.getText();
        // Consultar a la base de datos si el mail existe
        HttpResponse<String> response = Unirest.get("http://localhost:8080/user/checkExisteUser?email={mail}")
                .routeParam("mail",email)
                .asString();
        Boolean existe = Boolean.parseBoolean(response.getBody().toString());
        UserEmpleado userEmpleado = null;
        if (existe){
            userEmpleado = new UserEmpleado(email);
        }else {
            // poner un label que diga mail incorrecto
        }
        Email.clear();
        String horaInicial = horaInicio.getAccessibleText();
        String horaFinal = horaFin.getAccessibleText();
        LocalTime horaInicioLT = null;
        LocalTime horaFinalLT = null;
        if (horaInicial != null && horaFinal != null){
            horaInicioLT = LocalTime.parse(horaInicial);
            horaFinalLT = LocalTime.parse(horaFinal);
        }
        LocalDate fechaHoy = LocalDate.now();
        System.out.println(fechaHoy);

        String nombreServicio = Servicios.getValue().toString();
        System.out.println(nombreServicio);
        Servicio servico = null;

        for(int i = 0; i<listaServiciosO.size(); i++){
            System.out.println("Aca1");
            if(listaServiciosO.get(i).getKey().getNombre() == nombreServicio){
                servico = listaServiciosO.get(i);
            }
        }

        try {
            Ingreso nuevoIngreso = new Ingreso(fechaHoy,horaInicioLT,horaFinalLT,servico,userEmpleado);
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.findAndRegisterModules();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(df);
            String serialized = objectMapper.writeValueAsString(nuevoIngreso);
            HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/centroDeportivo/guardarIngreso") // HAY QUE DEFINIR LA HTTP BIEN
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(serialized)
                    .asJson();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Servicio> ListaServicios(){    // hay que hacer la consulta a la base de datos

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/centroDeportivo/listaServiciosEsteCentroDep")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            List<Servicio> listaServiciosEsteCentroDep = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Servicio>>(){});
            System.out.println(listaServiciosEsteCentroDep.size());
            return listaServiciosEsteCentroDep;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
