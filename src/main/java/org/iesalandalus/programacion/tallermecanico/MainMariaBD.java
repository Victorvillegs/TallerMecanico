package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class MainMariaBD {
    public static <Pair> void main(String[] args) {
        Pair<FabricaVista, FabricaFuenteDatos> fabricas = procesarArgumentos(args);
        IControlador controlador = new Controlador(FabricaModelo.CASCADA.crear(FabricaFuenteDatos.FICHEROS), FabricaVista.GRAFICA.crear());
        controlador.comenzar();
    }

    private static <Pair> Pair<FabricaVista, FabricaFuenteDatos> procesarArgumentos(String[] args) {
        FabricaVista fabricaVista = FabricaVista.GRAFICA;
        FabricaFuenteDatos fabricaFuenteDatos = FabricaFuenteDatos.MARIADB;
        for (String argumento : args) {
            if (argumento.equalsIgnoreCase("-vventanas")) {
                fabricaVista = FabricaVista.GRAFICA;
            } else if (argumento.equalsIgnoreCase("-vtexto")) {
                fabricaVista = FabricaVista.TEXTO;
            } else if (argumento.equalsIgnoreCase("-fdficheros")) {
                fabricaFuenteDatos = FabricaFuenteDatos.FICHEROS;
            } else if (argumento.equalsIgnoreCase("-fdmariadb")) {
                fabricaFuenteDatos = FabricaFuenteDatos.MARIADB;
            }
        }
    }
}
