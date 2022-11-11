package com.example.cliente;

import com.example.cliente.Model.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.example.cliente.Model.UserEmpleado;
import javafx.collections.FXCollections;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    @FXML
    private Label Lable;
    private ObservableList<String> listaServicios;
    private List<Servicio> listaServiciosO = ListaServicios();

    private List<Cancha> listaCanchas = ListaCanchas();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList listaNombreServicios = FXCollections.observableArrayList();
        for (int i = 0; i < listaServiciosO.size();i++){
            listaNombreServicios.add(listaServiciosO.get(i).getKey().getNombre());
        }

        ObservableList listaNombresCanchas = FXCollections.observableArrayList();
        for(int i = 0; i < listaCanchas.size(); i++){
            listaNombresCanchas.add(listaCanchas.get(i).getKey().getNombre());
        }

        listaServicios =  listaNombreServicios;
        for(int i = 0; i < listaServicios.size(); i++){
            Servicios.getItems().add(listaServicios.get(i));
        }

        for(int i = 0; i<listaNombresCanchas.size();i++){
            Servicios.getItems().add(listaNombresCanchas.get(i));
        }

//        Servicios.setValue(listaServicios);
    }

    public void desplegarHorarios(){
        ObservableList listaHorariosDisponibles = null;
        System.out.println("Entreeee");
        System.out.println(Servicios.getValue().toString());
        if(Servicios.getValue() == null){  // REVISAR QUE PASA, SI ES ASI O CON NULL
            horaInicio.setValue(listaHorariosDisponibles);
            horaFin.setValue(listaHorariosDisponibles);
        }
        else{
            String nombreServicio = Servicios.getValue().toString();
            Servicio servico = null;
            Cancha cancha = null;

            for(int i = 0; i<listaServiciosO.size(); i++){
                if(listaServiciosO.get(i).getKey().getNombre() == nombreServicio){
                    servico = listaServiciosO.get(i);
                    System.out.println("HOLAAA");
                    System.out.println(servico.getDescripcion());
                }
            }
            for(int i = 0; i<listaCanchas.size();i++){
                if(listaCanchas.get(i).getKey().getNombre() == nombreServicio){
                    cancha = listaCanchas.get(i);
                }
            }
            LocalTime horaInicio1 = null;
            LocalTime horaFin1 = null;

            if(servico != null){
                horaInicio1 = LocalTime.parse(servico.getHoraInicio());
                horaFin1 = LocalTime.parse(servico.getHoraFin());
            }
            else{
                horaInicio1 = LocalTime.parse(cancha.getHoraInicio());
                horaFin1 = LocalTime.parse(cancha.getHoraFin());
            }
            horaInicio.getItems().clear();
            horaFin.getItems().clear();
            for(int i = horaInicio1.getHour(); i <= horaFin1.getHour(); i++ ){
                System.out.println("Aca2");
                horaInicio.getItems().add(i + ":00");
                horaFin.getItems().add(i + ":00");

            }


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
            Lable.setText("Mail incorrecto");
        }
        Email.clear();

        String horaInicial = horaInicio.getValue().toString();
        String horaFinal = horaFin.getValue().toString();
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

        int inicio = horaInicioLT.getHour();
        int fin = horaFinalLT.getHour();

        int horasTotales = fin - inicio;
        long precio = horasTotales * servico.getPrecio();


        try {
            Ingreso nuevoIngreso = new Ingreso(fechaHoy, horaInicioLT,horaFinalLT, servico, userEmpleado, precio);
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

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/centroDeportivo/listaServiciosUnCentroDep")
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

    public List<Cancha> ListaCanchas(){    // hay que hacer la consulta a la base de datos

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/centroDeportivo/listaCanchasUnCentroDep")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            List<Cancha> listaServiciosEsteCentroDep = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Cancha>>(){});
            System.out.println(listaServiciosEsteCentroDep.size());
            return listaServiciosEsteCentroDep;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
