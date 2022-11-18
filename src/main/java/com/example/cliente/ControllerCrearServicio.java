package com.example.cliente;

import com.example.cliente.DTOs.ServicioDTO;
import com.example.cliente.Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Controller
public class ControllerCrearServicio implements Initializable {

    private Stage stage;
    private Scene scence;
    ObservableList<String> tipoList = FXCollections.observableArrayList("GYM", "Futbol", "Pisina", "Yoga", "Spinning", "Tennis", "Spinning");
    ObservableList<String> horariosList = FXCollections.observableArrayList("0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00");
    @FXML
    private TextField Nombre;
    @FXML
    private ComboBox choiceBoxTipo;
    @FXML
    private TextField Precio;
    @FXML
    private TextField Horarios;
    @FXML
    private ComboBox HorarioInicio;
    @FXML
    private ComboBox HorarioFin;
    @FXML
    private TextField Cupos;
    @FXML
    private TextField Descripcion;
    @FXML
    private Button BuscarImagen;
    @FXML
    private CheckBox DiaDomingo;

    @FXML
    private CheckBox DiaJueves;

    @FXML
    private CheckBox DiaLunes;

    @FXML
    private CheckBox DiaMartes;

    @FXML
    private CheckBox DiaMiercoles;

    @FXML
    private CheckBox DiaSabado;

    @FXML
    private CheckBox DiaViernes;

    private String imagen = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i<tipoList.size();i++){
            choiceBoxTipo.getItems().add(tipoList.get(i));
        }
        for(int k = 0; k<horariosList.size(); k++){
            HorarioFin.getItems().add(horariosList.get(k));
            HorarioInicio.getItems().add(horariosList.get(k));
        }

    }

    public void switchToAdminCentroDeporivo(javafx.event.ActionEvent event) throws IOException{
        guradarDatos();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(HelloApplication.class.getResourceAsStream("PrimeraVistaAdminCentroDeportivo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scence = new Scene(root);
        stage.setScene(scence);
        stage.show();
        stage.centerOnScreen();
    }

    public void buscarImagen(javafx.event.ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
//        fileChooser.showOpenDialog(stage);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("tengo file!!!");
        }
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            imagen = encodedString;
//            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/centroDeportivo/guardarFoto")
//                    .header("accept", "application/json")
//                    .header("Content-Type", "application/json")
//                    .body(encodedString)
//                    .asJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guradarDatos(){
        String nombre = Nombre.getText();
        String tipo = choiceBoxTipo.getValue().toString();
        Long precio = Long.valueOf(Integer.parseInt(String.valueOf(Precio.getText())));
//        String horarios = Horarios.getAccessibleText();
        String horarioInicio = HorarioInicio.getValue().toString();
        String horarioFin = HorarioFin.getValue().toString();
        String descripcion = Descripcion.getText();
        if (!Objects.equals(Cupos.getText(), "")){
            int cupos = Integer.parseInt(Cupos.getText());
        }

        Set<DiasDeLaSemana> diasSeleccionados = new HashSet<>();

        if(DiaLunes.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Lunes);
        }
        if(DiaMartes.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Martes);
        }
        if(DiaMiercoles.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Miercoles);
        }
        if(DiaJueves.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Jueves);
        }
        if(DiaViernes.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Viernes);
        }
        if(DiaSabado.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Sabado);
        }
        if(DiaDomingo.isSelected()==true){
            diasSeleccionados.add(DiasDeLaSemana.Domingo);
        }

        Nombre.clear();
//        choiceBoxTipo.clear(); no esxiste clear pero hay que ver si cuando cargas otro servicio mantiene el valor anterior
//        HorarioFin.clear();
//        HorarioInicio.clear();
        Precio.clear();
//        Horarios.clear();
        Cupos.clear();
        Set<Imagen> imagenes = new HashSet<>();
        imagenes.add(new Imagen(imagen));
        System.out.println(nombre);
        System.out.println(precio);
        System.out.println(diasSeleccionados);
        System.out.println(horarioInicio);
        System.out.println(horarioFin);
        System.out.println(descripcion);
        System.out.println(tipo);
        System.out.println(imagenes.size());
        ServicioDTO servicioDTO = new ServicioDTO(nombre, "", "", precio, diasSeleccionados, LocalTime.of(Integer.parseInt(horarioInicio),0), LocalTime.of(Integer.parseInt(horarioFin),0), descripcion, tipo, imagenes);

        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.findAndRegisterModules();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setDateFormat(df);
            String serialized = null;
            serialized = objectMapper.writeValueAsString(servicioDTO);

//            HttpResponse<JsonNode> response2 = Unirest.post("http://localhost:8080/user/crearServicioCentroDepDTO")
//                    .header("accept", "application/json")
//                    .header("Content-Type", "application/json")
//                    .body(serialized)
//                    .asJson();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
