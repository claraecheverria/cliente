package com.example.cliente;

import com.example.cliente.DTOs.CanchaDTO;
import com.example.cliente.DTOs.ReservaDTO;
import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ControllerSeleccionarHorariosReserva implements Initializable {

    @FXML
    private Button GuardarDatos;
    @FXML
    private Button Invitar;
    @FXML
    private TextField Mail;
    @FXML
    private VBox VboxHorarios;
    @FXML
    private VBox VboxMails;
    @FXML
    private Label Lable;
    @Autowired
    private ControllerSeleccionFechaReserva controllerSeleccionFechaReserva;
    private ControllerPlantillaServicio controllerPlantillaServicio;

    private ArrayList<Integer> horariosLibres = new ArrayList<Integer>();
    private List<UserEmpleado> mailsUsuarios = new ArrayList<>();
    private List<ReservaDTO> horariosOcupados;
    private ServicioDTO servicio;
    private ControllerSeleccionFechaReserva seleccionFechaReserva;
    
    public void setControllerPlantillaServicio(ControllerPlantillaServicio controller) {this.controllerPlantillaServicio = controller;}

    public void setServicio(ServicioDTO servicio) {
        VboxHorarios.getChildren().clear();
        VboxMails.getChildren().clear();
        horariosLibres.clear();

        this.servicio = servicio;
        LocalTime horaInicioServ = servicio.getHoraInicio();
        LocalTime horaFinServ = servicio.getHoraFin();
        for(int i = horaInicioServ.getHour(); i <= horaFinServ.getHour(); i++){
            horariosLibres.add(i);
        }
        horariosOcupados = getHorariosReservas();

        for(int j=0; j<horariosOcupados.size(); j++){
            int horaInicio = horariosOcupados.get(j).getHoraInicio().getHour();
            int horaFin = horariosOcupados.get(j).getHoraFin().getHour();
            int cantidadHoras = horaFin - horaInicio;
            for(int k = 0; k<cantidadHoras;k++){
                Integer elemento = horaInicio+k;
                horariosLibres.remove(elemento);
            }
        }

        try {
            for (int i = 0; i < horariosLibres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("BotonHorarioV2.fxml"));
                CheckBox horarioBox = fxmlLoader.load();
                ControllerPlantillaHorarioReserva servicioController = fxmlLoader.getController();
                servicioController.setData(horariosLibres.get(i).toString());
                VboxHorarios.getChildren().add(horarioBox);
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Lable.setText(" ");
    }

    public void invitarAmigo(javafx.event.ActionEvent actionEvent){
        CanchaDTO cancha = (CanchaDTO) servicio;

        if(mailsUsuarios.size() == cancha.getCupos()-1){//menos uno pq se cuenta el que esta haciendo la reserva
            Lable.setText("El cupo ya es el maximo permitido");
        }
        else{
            String mail = Mail.getText();
            Mail.clear();
            Lable.setText(" ");
            // Consultar a la base de datos si el mail existe
            HttpResponse<String> response = Unirest.get("http://localhost:8080/user/checkExisteUser?email={mail}")
                    .routeParam("mail",mail)
                    .asString();
            Boolean existe = Boolean.parseBoolean(response.getBody().toString());
            if(existe){
                VboxMails.getChildren().add(new Text(mail));
                UserEmpleado Usr = new UserEmpleado(mail);
                mailsUsuarios.add(Usr);
                System.out.println(mailsUsuarios.size());
            }
            else{
                Lable.setText("Seleccione un mail valido");
            }

        }

    }

    public List<ReservaDTO> getHorariosReservas(){
        LocalDate fecha = controllerSeleccionFechaReserva.mandarFecha();

        String centroDeportivoNombre = servicio.getNombreCentroDep();
        String servicioNombre = servicio.getNombreServicio();
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/reservasEnFechaDTO?fecha={fecha}&servicio={servicioNombre}&centroDep={centroDeportivoNombre}")
                .routeParam("fecha", String.valueOf(fecha))
                .routeParam("servicioNombre",servicioNombre)
                .routeParam("centroDeportivoNombre",centroDeportivoNombre)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(df);
        try {
            List<ReservaDTO> listaHorariosDisponibles = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<ReservaDTO>>(){});
            return listaHorariosDisponibles;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void guardarDatos(javafx.event.ActionEvent actionEvent){

        LocalDate fecha = controllerSeleccionFechaReserva.mandarFecha();
        CanchaDTO cancha = (CanchaDTO) servicio;
        ArrayList<CheckBox> listaBotonesSeleccionados = new ArrayList<>();

        for(int i = 0; i<horariosLibres.size();i++){
            if(((CheckBox)VboxHorarios.getChildren().get(i)).isSelected() == true){
                listaBotonesSeleccionados.add((CheckBox) VboxHorarios.getChildren().get(i));
            }
        }
        LocalTime horaInicioLT = LocalTime.parse("23:00:00");
        LocalTime horaFinLT = LocalTime.parse("00:00:00");

        for(int i = 0; i<listaBotonesSeleccionados.size();i++){
            String horaInicio = listaBotonesSeleccionados.get(i).getText();
            String horaFinal = listaBotonesSeleccionados.get(i).getText();
            Integer horaFinalInt = Integer.parseInt(horaFinal) + 1;
            horaFinal = horaFinalInt.toString();

            LocalTime horaInicio2 = LocalTime.of(Integer.parseInt(horaInicio),0);
            LocalTime horaFinal2 = LocalTime.of(Integer.parseInt(horaFinal),0);

            if(horaInicio2.compareTo(horaInicioLT)<0){
                horaInicioLT = horaInicio2;
            }
            if(horaFinal2.compareTo(horaFinLT)>0){
                horaFinLT = horaFinal2;
            }
        }
        ReservaDTO nuevaReserva = new ReservaDTO(fecha, horaInicioLT, horaFinLT, cancha.getNombreServicio(), cancha.getNombreCentroDep(), mailsUsuarios);
        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.findAndRegisterModules();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(df);
            String serialized = objectMapper.writeValueAsString(nuevaReserva);

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user/hacerReservaDTO")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(serialized)
                .asJson();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        volver(actionEvent);
        seleccionFechaReserva.volver(actionEvent);
    }

    public void setSeleccionFechaReserva(ControllerSeleccionFechaReserva controllerSeleccionFechaReserva) {
        this.seleccionFechaReserva = controllerSeleccionFechaReserva;
    }

    public void volver(javafx.event.ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
}
