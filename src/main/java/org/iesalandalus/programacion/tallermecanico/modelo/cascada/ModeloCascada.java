package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;

import java.time.LocalDate;
import java.util.List;



public class ModeloCascada implements Modelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos){
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }
    @Override
    public Modelo comenzar() {
        List<Cliente> ListaCliente;
        List<Vehiculo> ListaVehiculo;
        List<Trabajo> ListaTrabajo;
        return null;
    }

    @Override
    public Modelo terminar() {
        return null;
    }

    @Override
    public void insertar(Cliente cliente) {
        return insertar(Cliente cliente);
    }

    @Override
    public void insertar(Vehiculo vehiculo) {
        insertar(vehiculo vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) {

    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return null;
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return null;
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        return null;
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) {
        return false;
    }

    @Override
    public void añadorHoras(Trabajo trabajo, int horas) {

    }

    @Override
    public void añadorPrecioMaterial(Trabajo trabajo, float precioMaterial) {

    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) {

    }

    @Override
    public void borrar(Cliente cliente) {

    }

    @Override
    public void borrar(Vehiculo vehiculo) {

    }

    @Override
    public void borrar(Trabajo trabajo) {

    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return null;
    }

    @Override
    public List<Cliente> getClientes() {
        return null;
    }

    @Override
    public List<Trabajo> getTrabajos() {
        return null;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        return null;
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        return null;
    }
}
