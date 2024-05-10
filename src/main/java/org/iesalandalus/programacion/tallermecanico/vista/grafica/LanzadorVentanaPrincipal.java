package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;

import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class LanzadorVentanaPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    Controlador ventanaPrincipal = Controladores.get("/vista/VentanaPrincipal.fxml", "Taller mecanico", null);
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().show();
    }

    public static void comenzar(){
        launch(LanzadorVentanaPrincipal.class);
    }
}
