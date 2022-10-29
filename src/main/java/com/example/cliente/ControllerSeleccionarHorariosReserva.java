package com.example.cliente;

import com.example.cliente.Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    //@Autowired
    //private nombreController nombreAtributo;

    @Autowired
    private ControllerSeleccionFechaReserva controllerSeleccionFechaReserva;
    private ControllerPlantillaServicio controllerPlantillaServicio;

    private List<Reserva> horariosLibres;
    private List<UserEmpleado> mailsUsuarios;

    private Servicio servicio;

    public void setServicio(Servicio servicio) {
        System.out.println(servicio.getKey().getNombre());
        this.servicio = servicio;
        horariosLibres = getHorariosReservas();
        try {
            for (int i = 0; i < horariosLibres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("BotonHorario.fxml"));
                AnchorPane horarioBox = fxmlLoader.load();
                ControllerPlantillaHorarioReserva servicioController = fxmlLoader.getController();
                servicioController.setData(horariosLibres.get(i).getHoraFin().toString());
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
//        horariosLibres = getHorariosReservas();
//        try {
//            for (int i = 0; i < horariosLibres.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
//                fxmlLoader.setLocation(getClass().getResource("BotonHorario.fxml"));
//                HBox horarioBox = fxmlLoader.load();
//                ControllerPlantillaHorarioReserva servicioController = fxmlLoader.getController();
//                servicioController.setData(horariosLibres.get(i).getHoraFin().toString());
//                VboxHorarios.getChildren().add(horarioBox);
//            }
//        }
//        catch(IOException e){
//            throw new RuntimeException(e);
//        }
    }

    public void invitarAmigo(javafx.event.ActionEvent actionEvent){
        String mail = Mail.toString();
        User nuevoUser = new User(mail);

        // Consultar a la base de datos si el mail existe
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/checkExisteUser?email={mail}")
                .routeParam("mail",mail)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        System.out.println(response.getBody());

        if(response.getBody().toString() == "true"){
            VboxMails.getChildren().add(new Text(mail));
            User user = new User(mail);
            mailsUsuarios.add((UserEmpleado) user);
        }
        else{
            Lable.setText("Seleccione un mail valido");
        }
    }

    public List<Reserva> getHorariosReservas(){   // HAY QUE PONER QUE DEVUELVA LOS HORARIOS LIBRES
        //Tengo que mandar nombre centro deportivo, nomber servicio y fecha

//        ControllerPlantillaServicio controllerPlantillaServicio1 = (ControllerPlantillaServicio) ClienteApplication.getContext().getBean("controllerPlantillaServicio");

//        CentroDeportivo centroDeportivo = controllerPlantillaServicio1.devolverServicio().getCentroDeportivoServicio();
//        Servicio servicio = controllerPlantillaServicio1.devolverServicio();
        LocalDate fecha = controllerSeleccionFechaReserva.mandarFecha();

        String centroDeportivoNombre = servicio.getCentroDeportivoServicio().getNombre();
        String servicioNombre = servicio.getKey().getNombre();
        System.out.println(fecha);
        System.out.println(String.valueOf(fecha));

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/reservasEnFecha?fecha={fecha}&servicio={servicioNombre}&centroDep={centroDeportivoNombre}")
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
            List<Reserva> listaHorariosDisponibles = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Reserva>>(){});
            System.out.println(listaHorariosDisponibles.size());
            return listaHorariosDisponibles;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void guardarDatos(javafx.event.ActionEvent actionEvent){

        LocalDate fecha = controllerSeleccionFechaReserva.mandarFecha();
        Cancha cancha = controllerPlantillaServicio.devolverCancha();

        ArrayList<Button> listaBotonesSeleccionados = new ArrayList<>();
        for(int i = 0; i<horariosLibres.size();i++){
            if(VboxHorarios.getChildren().get(i).isPressed() == true){
                listaBotonesSeleccionados.add((Button) VboxHorarios.getChildren().get(i));
            }
        }
        LocalTime horaInicioLT = LocalTime.parse("23:00:00");
        LocalTime horaFinLT = LocalTime.parse("00:00:00");

        for(int i = 0; i<listaBotonesSeleccionados.size();i++){
            String horaInicio = listaBotonesSeleccionados.get(i).toString();
            LocalTime horaInicio2 = LocalTime.parse(horaInicio);

            if(horaInicio2.compareTo(horaInicioLT)<0){
                horaInicioLT = horaInicio2;
            }
            if(horaInicio2.compareTo(horaFinLT)>0){
                horaFinLT = horaInicio2;
            }
        }
        Reserva nuevaReserva = new Reserva(fecha, horaInicioLT,horaFinLT,cancha,mailsUsuarios); // mailUsuarios deberia ser con los usuarioEmpleado

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user/hacerReserva")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevaReserva)
                .asJson();
    }

}
