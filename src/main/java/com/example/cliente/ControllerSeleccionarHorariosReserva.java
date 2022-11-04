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

    private ArrayList<Integer> horariosLibres = new ArrayList<Integer>();
    private List<UserEmpleado> mailsUsuarios = new ArrayList<>();
    private List<Reserva> horariosOcupados;
    private Servicio servicio;


    public void setServicio(Servicio servicio) {
        System.out.println(servicio.getKey().getNombre());
        this.servicio = servicio;
        horariosOcupados = getHorariosReservas();

        LocalTime horaInicioServ = LocalTime.parse(servicio.getHoraInicio());
        LocalTime horaFinServ = LocalTime.parse(servicio.getHoraFin());
        for(int i = horaInicioServ.getHour(); i <= horaFinServ.getHour(); i++){
            horariosLibres.add(i);
            System.out.println(i);
        }

        for(int j=0; j<horariosOcupados.size(); j++){

            int horaInicio = Integer.parseInt(String.valueOf(horariosOcupados.get(j).getHoraInicio().getHour()));
            System.out.println(horaInicio);
            int horaFin = Integer.parseInt(String.valueOf(horariosOcupados.get(j).getHoraFin().getHour()));
            System.out.println(horaFin);
            int cantidadHoras = horaFin - horaInicio;
            System.out.println(cantidadHoras);

            for(int k = 0; k<cantidadHoras;k++){
//                horariosLibres.remove(horaInicio+k);
                Integer elemento = horaInicio+k;
                horariosLibres.remove(elemento);

            }
        }

        try {
            for (int i = 0; i < horariosLibres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("BotonHorarioV2.fxml"));
                Button horarioBox = fxmlLoader.load();
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
            //hay que ver que pasa cuando te devuelve una lista vacía, pq sino en el objectmapper se rompe
            List<Reserva> listaHorariosDisponibles = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Reserva>>(){});
            System.out.println(listaHorariosDisponibles.size());
            return listaHorariosDisponibles;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void guardarDatos(javafx.event.ActionEvent actionEvent){

        LocalDate fecha = controllerSeleccionFechaReserva.mandarFecha();
//        Cancha cancha = controllerPlantillaServicio.devolverCancha();
        Cancha cancha = (Cancha) servicio;
        ArrayList<Button> listaBotonesSeleccionados = new ArrayList<>();
        for(int i = 0; i<horariosLibres.size();i++){
            if(VboxHorarios.getChildren().get(i).getStyleClass().toString() == "-fx-background-color: #1b68b3;"){
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
