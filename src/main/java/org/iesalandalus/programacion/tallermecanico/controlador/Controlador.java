package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {

    private Modelo modelo;
    private Vista vista;
    public Controlador (Modelo modelo,Vista vista){
        Objects.requireNonNull(modelo,"El modelo no puede ser nulo.");
        Objects.requireNonNull(vista,"La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }
    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();
    }
    public void terminar(){
        modelo.terminar();
        vista.terminar();
    }
    public void insertar(Cliente cliente)throws OperationNotSupportedException{
        modelo.insertar(cliente);
    }
    public void insertar(Vehiculo vehiculo)throws OperationNotSupportedException{
        modelo.insertar(vehiculo);
    }
    public void insertar(Trabajo trabajo)throws OperationNotSupportedException{
        modelo.insertar(trabajo);
    }
    public Cliente buscar(Cliente cliente){
        return modelo.buscar(cliente);
    }
    public Vehiculo buscar(Vehiculo vehiculo){
        return modelo.buscar(vehiculo);
    }
    public Trabajo buscar(Trabajo trabajo){
        return modelo.buscar(trabajo);
    }
    public void anadirHoras (Trabajo trabajo, int horas) throws OperationNotSupportedException{
        modelo.anadirHoras(trabajo,horas);
    }
    public void anadirPrecioMaterial (Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException{
        modelo.anadirPrecioMaterial(trabajo,precioMaterial);
    }
    public void cerrar (Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException{
        modelo.cerrar(trabajo,fechaFin);
    }
    public void borrar(Cliente cliente)throws OperationNotSupportedException{
        modelo.borrar(cliente);
    }
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException{
        modelo.borrar(vehiculo);
    }
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException{
        modelo.borrar(trabajo);
    }
    public List<Cliente> getClientes(){
        return modelo.getClientes();
    }
    public List<Vehiculo> getVehiculos() {
        return modelo.getVehiculos();
    }

    public List<Trabajo> getRevisiones() {
        return modelo.getRevisiones();
    }

    public List<Trabajo> getRevisiones(Cliente cliente) {
        return modelo.getRevisiones(cliente);
    }

    public List<Trabajo> getRevisiones(Vehiculo vehiculo) {
        return modelo.getRevisiones(vehiculo);
    }
    public boolean modificar(Cliente cliente,String nombre, String telefono) throws  OperationNotSupportedException {
        return modelo.modificar(cliente,nombre,telefono);
    }

}
