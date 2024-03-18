package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Trabajos trabajos;

    public Modelo() {
        comenzar();
    }

    public void comenzar() {
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.trabajos = new Trabajos();
    }

    public void terminar() {
        System.out.println("El Modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        this.clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        this.vehiculos.insertar(vehiculo);
    }

    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Cliente c = buscar(trabajo.getCliente());
        Vehiculo v = buscar(trabajo.getVehiculo());
        this.trabajos.insertar(new Revision(c,v, trabajo.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        return this.clientes.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return  this.vehiculos.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo trabajo) {
        return this.trabajos.buscar(trabajo);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono)throws OperationNotSupportedException {
        return this.clientes.modificar(cliente,nombre,telefono);
    }

    public void anadirHoras(Trabajo trabajo, int horas)throws OperationNotSupportedException {
        this.trabajos.anadirHoras(trabajo,horas);
    }

    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial)throws OperationNotSupportedException {
        this.trabajos.anadirPrecioMaterial(trabajo,precioMaterial);
    }
    public void cerrar(Trabajo trabajo, LocalDate fechaFin)throws OperationNotSupportedException{
        this.trabajos.cerrar(trabajo,fechaFin);
    }
    public void borrar (Vehiculo vehiculo) throws OperationNotSupportedException{
        List<Trabajo> aux = this.trabajos.get(vehiculo);
        for (Trabajo r : aux){
            this.trabajos.borrar(r);
        }
        this.vehiculos.borrar(vehiculo);
    }
    public void borrar (Cliente cliente) throws OperationNotSupportedException{
        List<Trabajo> aux = this.trabajos.get(cliente);
        for (Trabajo r : aux){
            this.trabajos.borrar(r);
        }
        this.clientes.borrar(cliente);
    }
    public void borrar (Trabajo trabajo) throws OperationNotSupportedException{
            this.trabajos.borrar(trabajo);
    }
    public List<Cliente> getClientes(){
        List<Cliente> aux = new ArrayList<>();
        for (Cliente c : this.clientes.get()){
            aux.add(new Cliente(c));
        }
        return aux;
    }
    public List<Vehiculo> getVehiculos(){
        return this.vehiculos.get();
    }
    public List<Trabajo> getRevisiones(){
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo r : this.trabajos.get()){
            aux.add(new Revision((Revision) r));
        }
        return aux;
    }
    public List<Trabajo> getRevisiones(Cliente cliente){
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo r : this.trabajos.get(cliente)){
            aux.add(new Revision((Revision) r));
        }
        return aux;
    }
    public List<Trabajo> getRevisiones(Vehiculo vehiculo){
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo r : this.trabajos.get(vehiculo)){
            aux.add(new Revision((Revision) r));
        }
        return aux;
    }
}
