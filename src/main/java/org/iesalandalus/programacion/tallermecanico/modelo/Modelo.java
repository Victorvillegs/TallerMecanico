package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public interface Modelo {
    public Modelo comenzar();
    public Modelo terminar();
    public void insertar(Cliente cliente);
    public void insertar(Vehiculo vehiculo);
    public void insertar(Trabajo trabajo);
    public Cliente buscar(Cliente cliente);
    public Vehiculo buscar(Vehiculo vehiculo);
    public Trabajo buscar(Trabajo trabajo);
    public boolean modificar(Cliente cliente, String nombre, String telefono);
    public void añadorHoras(Trabajo trabajo, int horas);
    public void añadorPrecioMaterial(Trabajo trabajo, float precioMaterial);
    public void cerrar(Trabajo trabajo, LocalDate fechaFin);
    public void borrar(Cliente cliente);
    public void borrar(Vehiculo vehiculo);
    public void borrar(Trabajo trabajo);
    public List<Vehiculo> getVehiculos();
    public List<Cliente> getClientes();
    public List<Trabajo> getTrabajos();
    public List<Trabajo> getTrabajos(Cliente cliente);
    public List<Trabajo> getTrabajos(Vehiculo vehiculo);


}
