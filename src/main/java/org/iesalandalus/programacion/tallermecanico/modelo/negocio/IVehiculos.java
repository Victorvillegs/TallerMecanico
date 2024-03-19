package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IVehiculos {
    public List<Vehiculo> get();
    public void insertar(Vehiculo vehiculo)throws OperationNotSupportedException;
    public Vehiculo buscar (Vehiculo vehiculo);
    public void borrar (Vehiculo vehiculo)throws OperationNotSupportedException;
}
