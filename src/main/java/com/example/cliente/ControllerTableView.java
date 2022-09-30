package com.example.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ControllerTableView {

    public Button btnActualizar;

    // COLUMNAS TABLA EMPLEADOS
    public TableColumn<TableView<User>, String> colCorreo;
    public TableColumn<TableView<User>, String> colNombres;
    public TableColumn<TableView<User>, String> colApellidos;
    public TableColumn<TableView<User>, Integer> colCedula;
    public TableColumn<TableView<User>, LocalDate> colVenCarne;
    public TableColumn<TableView<User>, Double> colSaldo;
    public TableView<User> tableEmpleado;
    private ObservableList<User> empleados;


    public void actualizar (ActionEvent actionEvent) {
        empleados = FXCollections.observableArrayList();
        this.colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory<>("mail"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory<>("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        this.tableEmpleado.setItems(empleados);
    }
}
