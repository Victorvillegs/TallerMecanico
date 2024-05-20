package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class VentanaPrincipal extends Controlador {

    @FXML
    private Button btCBorrar;

    @FXML
    private Button btCBuscar;

    @FXML
    private Button btCInsertar;

    @FXML
    private Button btTBorrar;

    @FXML
    private Button btTBuscar;

    @FXML
    private Button btTInsertar;

    @FXML
    private Button btVBorrar;

    @FXML
    private Button btVBuscar;

    @FXML
    private Button btVInsertar;

    @FXML
    private ImageView lbImagen;
    @FXML
    private MenuItem Ssalir;
    private String dniLeido;
    public String getDniLeido(){
        return dniLeido;
    }
    @FXML
    void borrarCliente(ActionEvent event) {


    }

    @FXML
    void buscarCliente(ActionEvent event) {
        if(dniLeido != null){
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }
    }

    @FXML
    void insertarCliente() {
        InsertarCliente InsertarCliente = (InsertarCliente) Controladores.get("/vista/InsertarCliente.fxml", "Pulsaciones boton", null);
        InsertarCliente.getEscenario().showAndWait();
        if (!InsertarCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }

    }

    @FXML
    void salir(ActionEvent event) {

    }
}


