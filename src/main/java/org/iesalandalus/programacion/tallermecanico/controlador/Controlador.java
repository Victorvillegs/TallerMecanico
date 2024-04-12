package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Consola;

import java.util.Objects;

public class Controlador implements IControlador {

    private final Modelo modelo;
    private final Vista vista;


    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
        vista.getGestorEventos().suscribir(this, Evento.values());
    }

    @Override
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }


    public void actualizar(Evento evento) {
        String resultado = "";
        try {
            switch (evento) {
                case INSERTAR_CLIENTE -> {
                    modelo.insertar(vista.leerCliente());
                    resultado = "Cliente insertado correctamente.";
                }
                case BUSCAR_CLIENTE -> vista.mostrarCliente(modelo.buscar(vista.leerClienteDni()));
                case BORRAR_CLIENTE -> {
                    modelo.borrar(modelo.buscar(vista.leerClienteDni()));
                    resultado = "Cliente eliminado correctamente.";
                }
                case LISTAR_CLIENTES -> vista.mostrarClientes(modelo.getClientes());
                case MODIFICAR_CLIENTE -> {
                    if (modelo.modificar(vista.leerClienteDni(), vista.leerNuevoNombre(), vista.leerNuevoTelefono())) {
                        resultado = "Cliente modificado correctamente.";
                    } else {
                        resultado = "El cliente no se ha podido modificar.";
                    }
                }
                case INSERTAR_VEHICULO -> {
                    modelo.insertar(vista.leerVehiculo());
                    resultado = "Vehículo insertado correctamente.";
                }
                case BUSCAR_VEHICULO -> vista.mostrarVehiculo(modelo.buscar(vista.leerVehiculoMatricula()));
                case BORRAR_VEHICULO -> {
                    modelo.borrar(modelo.buscar(vista.leerVehiculoMatricula()));
                    resultado = "Vehículo eliminado correctamente.";
                }
                case LISTAR_VEHICULOS -> vista.mostrarVehiculos(modelo.getVehiculos());
                case INSERTAR_REVISION -> {
                    modelo.insertar(new Revision(modelo.buscar(vista.leerClienteDni()), modelo.buscar(vista.leerVehiculoMatricula()), Consola.leerFecha("Introduce la fecha de inicio de la revisión:")));
                    resultado = "Revisión insertada correctamente.";
                }
                case INSERTAR_MECANICO -> {
                    modelo.insertar(new Mecanico(modelo.buscar(vista.leerClienteDni()), modelo.buscar(vista.leerVehiculoMatricula()), Consola.leerFecha("Introduce la fecha de inicio del trabajo mecánico:")));
                    resultado = "Trabajo mecánico insertado correctamente.";
                }
                case BUSCAR_TRABAJO -> {
                    Trabajo trabajo = new Revision(modelo.buscar(vista.leerClienteDni()), modelo.buscar(vista.leerVehiculoMatricula()), Consola.leerFecha("Introduce la fecha de inicio:"));
                    Trabajo trabajoEncontrado = modelo.buscar(trabajo);
                    if (trabajoEncontrado == null) {
                        trabajo = new Mecanico(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
                    }
                    vista.mostrarTrabajo(trabajo);
                }
                case BORRAR_TRABAJO -> modelo.borrar(modelo.buscar(vista.leerTrabajoVehiculo()));
                case LISTAR_TRABAJOS -> vista.mostrarTrabajos(modelo.getTrabajos());
                case LISTAR_TRABAJOS_CLIENTE -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerClienteDni()));
                case LISTAR_TRABAJOS_VEHICULO ->
                        vista.mostrarTrabajos(modelo.getTrabajos(vista.leerVehiculoMatricula()));
                case ANADIR_HORAS_TRABAJO -> {
                    modelo.anadirHoras(modelo.buscar(vista.leerMecanico()), vista.leerHoras());
                    resultado = "Las horas se han añadido correctamente.";
                }
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> {
                    modelo.anadirPrecioMaterial(new Mecanico(modelo.buscar(vista.leerClienteDni()), modelo.buscar(vista.leerVehiculoMatricula()), Consola.leerFecha("Introduce la fecha de inicio:")), vista.leerPrecioMaterial());
                    resultado = "El precio del material se ha añadido correctamente.";
                }
                case CERRAR_TRABAJO -> {
                    modelo.cerrar(vista.leerMecanico(), vista.leerFechaCierre());
                    resultado = "El trabajo se ha cerrado correctamente.";
                }
                case SALIR -> System.out.println("Usted ha salido de la aplicación.");
            }
            if (!resultado.isBlank()) {
                vista.notificarResultado(evento, resultado, true);
            }
        } catch (Exception e) {
            vista.notificarResultado(evento, e.getMessage(), false);
        }
    }
}
