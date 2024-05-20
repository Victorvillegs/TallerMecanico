package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class LanzadorVentanaPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    Controlador ventanaPrincipal = Controladores.get("/vista/VentanaPrincipal.fxml", "Taller mecanico", null);
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().setOnCloseRequest(e -> salir(e, ventanaPrincipal.getEscenario()));
        ventanaPrincipal.getEscenario().show();
    }

    public static void comenzar(){
        launch(LanzadorVentanaPrincipal.class);
    }

    void salir(WindowEvent e, Stage escenario){
        if(Dialogos.mostrarDialogoConfirmacion("Salir", "Estas seguro de querer salir de la aplicacion?", escenario)){
            escenario.close();
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);
        }else{
            e.consume();
        }
    }
}
