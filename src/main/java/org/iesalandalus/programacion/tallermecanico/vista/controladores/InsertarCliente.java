package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class InsertarCliente extends Controlador {

    private boolean cancelado= false;
    @FXML
    private Button btCancelar;

    @FXML
    private Button btInsertar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    public Cliente getCliente(){
        String dni = tfDni.getText();
        String nombre = tfNombre.getText();
        String telefono = tfTelefono.getText();
        return new Cliente(nombre, dni, telefono);
    }
    @FXML
    void cancelar(ActionEvent event) {
        cancelado = true;
        getEscenario().close();

    }

    public boolean isCancelado(){
        return cancelado;
    }

    @FXML
    void insertarCli(ActionEvent event) {
        cancelado = false;
        getEscenario().close();
    }

}


