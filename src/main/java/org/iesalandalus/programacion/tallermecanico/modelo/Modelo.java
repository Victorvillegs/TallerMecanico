package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Modelo {
    public void comenzar();
    public void terminar();
    public void insertar(Cliente cliente) throws OperationNotSupportedException;
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException;
    public Cliente buscar(Cliente cliente);
    public Vehiculo buscar(Vehiculo vehiculo);
    public Trabajo buscar(Trabajo trabajo);
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException;
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException;
    public void cerrar(Trabajo trabajo, LocalDate fechaFin)throws OperationNotSupportedException;
    public void borrar(Cliente cliente)throws OperationNotSupportedException;
    public void borrar(Vehiculo vehiculo)throws OperationNotSupportedException;
    public void borrar(Trabajo trabajo)throws OperationNotSupportedException;
    public List<Vehiculo> getVehiculos();
    public List<Cliente> getClientes();
    public List<Trabajo> getTrabajos();
    public List<Trabajo> getTrabajos(Cliente cliente);
    public List<Trabajo> getTrabajos(Vehiculo vehiculo);
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes);

}
