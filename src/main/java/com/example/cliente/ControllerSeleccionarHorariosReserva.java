package com.example.cliente;

import com.example.cliente.Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private ArrayList horariosLibres;
    private List<UserEmpleado> mailsUsuarios;

    private Servicio servicio;

    public void setServicio(Servicio servicio) {
        System.out.println(servicio.getKey().getNombre());
        this.servicio = servicio;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        horariosLibres = (ArrayList) getHorariosReservas();
//        try {
//            for (int i = 0; i < horariosLibres.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
////                fxmlLoader.setControllerFactory(ClienteApplication.getContext()::getBean);
//                fxmlLoader.setLocation(getClass().getResource("BotonHorario.fxml"));
//                HBox horarioBox = fxmlLoader.load();
//                ControllerPlantillaHorarioReserva servicioController = fxmlLoader.getController();
//                servicioController.setData((String) horariosLibres.get(i));
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
        boolean existe = true;

        // Consultar a la base de datos si el mail existe
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user/userParaCheck")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(nuevoUser)
                .asJson();

        // Que me llegue un booleano diciendo si existe o no el usuario
        // En caso de que no exista settear el Lable en "seleccione un mail valido"
        // En caso de que exista, que me llegue desde la base de datos el usuario por que despues le voy a tener que agregar la reserva

        if(existe == true){
            VboxMails.getChildren().add(new Text(mail));
            User user = new User(mail);
            mailsUsuarios.add((UserEmpleado) user);
        }
        else{
            Lable.setText("Seleccione un mail valido");
        }

    }

    public List getHorariosReservas(){   // HAY QUE PONER QUE DEVUELVA LOS HORARIOS LIBRES
        //Tengo que mandar nombre centro deportivo, nomber servicio y fecha
//        ControllerPlantillaServicio controllerPlantillaServicio1 = (ControllerPlantillaServicio) ClienteApplication.getContext().getBean("controllerPlantillaServicio");

//        CentroDeportivo centroDeportivo = controllerPlantillaServicio1.devolverServicio().getCentroDeportivoServicio();
//        Servicio servicio = controllerPlantillaServicio1.devolverServicio();
//        LocalDate fecha = controllerSeleccionFechaReserva.mandarFecha();

//        String centroDeportivoNombre = centroDeportivo.getNombre();
//        String servicioNombre = servicio.getKey().getNombre();

//        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/user/listaServicios")
//                .header("accept", "application/json")
//                .header("Content-Type", "application/json")
//                .asJson();
//        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
//        try {
//            List<Servicio> listaHorariosDisponibles = objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Servicio>>(){});
//            System.out.println(listaHorariosDisponibles.size());
//            return listaHorariosDisponibles;
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return new ArrayList<>();
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
