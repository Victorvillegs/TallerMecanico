package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    private final List<Trabajo> coleccionRevisiones;
    public Trabajos() {
        this.coleccionRevisiones = new ArrayList<>();
    }

    public List<Trabajo> get(){
        return this.coleccionRevisiones;
    }

    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo r : coleccionRevisiones){
            if (r.getCliente().equals(cliente)){
                aux.add(r);
            }
        }
        return aux;
    }

    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo r : coleccionRevisiones){
            if (r.getVehiculo().equals(vehiculo)){
                aux.add(r);
            }
        }
        return aux;
    }

    public void insertar(Trabajo trabajo)throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar una revisión nula.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        this.coleccionRevisiones.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Trabajo r : coleccionRevisiones){
            if (!r.estaCerrado()){
                if (r.getCliente().equals(cliente)){
                    throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
                } else if (r.getVehiculo().equals(vehiculo)) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
                }
            }else {
                if (r.getCliente().equals(cliente) && !fechaRevision.isAfter(r.getFechaFin())){
                    throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
                } else if (r.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(r.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    public void anadirHoras(Trabajo trabajo, int horas)throws OperationNotSupportedException {
        Trabajo r = getTrabajoAbierto(trabajo);
        r.anadirHoras(horas);
    }

    private Trabajo getTrabajoAbierto(Trabajo trabajo)throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo operar sobre una revisión nula.");
        for (Trabajo r : coleccionRevisiones){
            if (r.equals(trabajo)){
                return r;
            }
        }
        throw new OperationNotSupportedException("No existe ninguna revisión igual.");
    }

    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial)throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo,"No puedo operar sobre una revisión nula.");
        Trabajo r = getTrabajoAbierto(trabajo);
        //r.anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Trabajo trabajo, LocalDate fechaFin)throws OperationNotSupportedException {
        Trabajo r = getTrabajoAbierto(trabajo);
        r.cerrar(fechaFin);
    }

    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo,"No se puede buscar una revisión nula.");
        for (Trabajo r : coleccionRevisiones){
            if (r.equals(trabajo)){
                return r;
            }
        }
        return null;
    }

    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar una revisión nula.");
        Trabajo r = getTrabajoAbierto(trabajo);
        this.coleccionRevisiones.remove(r);
    }
}
