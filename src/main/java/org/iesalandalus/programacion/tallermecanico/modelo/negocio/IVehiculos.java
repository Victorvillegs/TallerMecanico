package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;

public interface IVehiculos {
    public List<Vehiculo> get();
    public void insertar(Vehiculo vehiculo);
    public Cliente buscar (Vehiculo vehiculo);
    public void borrar (Vehiculo vehiculo);
}
