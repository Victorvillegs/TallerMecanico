package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

public class FuenteDeMemoria implements IFuenteDatos {

    @Override
    public IClientes crearClientes() {
        return null;
    }

    @Override
    public IVehiculos crearVehiculos() {
        return null;
    }

    @Override
    public ITrabajos crearTrabajos() {
        return null;
    }
}
