package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {
    private List<Vehiculo> coleccionVehiculos;
    public Vehiculos() {
        this.coleccionVehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return this.coleccionVehiculos;
    }

    public void insertar(Vehiculo vehiculo)throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo,"No se puede insertar un vehículo nulo.");
        Vehiculo v = buscar(vehiculo);
        if (v !=null) throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        this.coleccionVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        for (Vehiculo v : coleccionVehiculos){
            if (v.equals(vehiculo)){
                return v;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        Vehiculo v = buscar(vehiculo);
        if (v == null) throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        this.coleccionVehiculos.remove(v);
    }
}
